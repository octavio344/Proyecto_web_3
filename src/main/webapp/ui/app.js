let moduloPedidos=angular.module('final-iw3',['ngStorage', 'ngStomp', 'oitozero.ngSweetAlert'])

    .constant('URL_API_BASE', 'http://localhost:8080/api/v1/')
    .constant('URL_BASE', 'http://localhost:8080/')
    .constant('URL_WS', '/api/v1/ws')


moduloPedidos.controller('pedidosController', function($scope, $rootScope, $timeout, $interval, $log, $localStorage, pedidosService, wsService, $stomp, SweetAlert, $http){



    $rootScope.stomp = $stomp;

    //Verifico si el usuario está logueado, y si no está logueado lo redirecciono a la página de login

    if($localStorage.logged!=true)
        window.location.replace("/login.html");


    //Inicializo las variables con sus valores por defecto

    $scope.orderby = "nroOrden"
    $scope.mostrarPedidos = true;
    $scope.mostrarConciliacion = false;
    $scope.mostrarAlarmas = false;
    $scope.idConciliacion = 0;
    $scope.selectValue = "ID";
    $scope.numero = "1";
    $scope.fecha = new Date();
    $scope.filtrarAnulado = "No anulado"

    //Obtengo el token del usuario

    let token = $localStorage.userdata.authtoken;

    //Función encargada de cargar los pedidos desde la API

    $scope.cargarPedidos = function (){
        pedidosService.cargar(token).then(
        function(resp){
                $scope.data=resp.data;
                $scope.totalDeItems = $scope.data.length;

                //Guardo una copia de los datos originales, para que no se pierdan al filtrar
                $scope.originalData = resp.data;

            },

            //Si ocurrio un error, posiblemente un 403, redirecciono a la página de login
            function(err){window.location.replace("/login.html");}
        );

    }
    //Cargo los pedidos ya que se deben cargarse si o si al abrir la página

    $scope.cargarPedidos();

    //Muestra el div de los pedidos y oculta los divs de conciliacion y alarma al presionar "Ver Pedidos" en el menú superior

    $scope.mostrarPaginaPedidos = function(){
        $scope.mostrarPedidos = true;
        $scope.mostrarConciliacion = false;
        $scope.mostrarAlarmas = false;
    }

    //Carga la conciliación al apretar el botón "Ver Conciliación" en la tabla de pedidos

    $scope.cargarConciliacion = function (idPedido){
        pedidosService.cargarConc(idPedido, token).then(
            function(resp){
                $scope.mostrarPedidos = false;
                $scope.mostrarAlarmas = false;
                $scope.mostrarConciliacion = true;
                console.log($scope.mostrarConciliacion);
                $scope.idConciliacion = idPedido;
                $scope.dataConc=resp.data;
            },
            function(err){}
        );
    }

    //Inicio el websocket, que se encargará de actualizar la tabla en tiempo real y enviar las alarmas

    $scope.iniciaWS = function() {
        wsService.initStompClient('/iw3/data', function(payload,
                                                                 headers, res) {
            if(res!=null){
                let resSplit = res.toString().split("\n");
                let respuesta = resSplit[resSplit.length-1];

                //Si incluye "TYPE=" es porque es una alarma

                if(respuesta.includes("TYPE=")){
                    let tipo = respuesta.split("TYPE=")[1];
                    respuesta = respuesta.split("TYPE=")[0];
                    $scope.nroOrden = respuesta.split("orden ")[1].split(" ")[0];
                    $scope.motivoAlarma = respuesta;
                    let titulo = "";
                    let logoAlarma = "error";

                    //Veo de que tipo es la alarma para poner el título y el ícono adecuados
                    if(tipo=="excesoTemp")
                        titulo="Exceso de Temperatura detectado"
                    else if(tipo=="90preset"){
                        logoAlarma="warning";
                        titulo="90% del preset alcanzado"
                    }
                    else{
                        titulo="Preset Superado"
                    }

                    //Muestro la alarma usando la librería SweetAlert
                    SweetAlert.swal({
                            title: titulo,
                            text: respuesta,
                            type: logoAlarma,
                            showCancelButton: false,
                            confirmButtonColor: "#FF0000",
                            confirmButtonText: "Aceptar alarma",
                            closeOnConfirm: true},
                        function(){
                            $scope.aceptarAlarma();
                        });
                }

                //Si no es una alarma, es una actualización de tabla, entonces actualizo la misma y aplico filtros por si los había
                else{
                    $scope.originalData = JSON.parse(respuesta);
                    $scope.filtrarTabla();
                    $scope.$apply();
                }
            }
        }, $scope.stomp);
    }

    //Inicio el Web Socket para cargar
    $scope.iniciaWS();

    //Función para detener el Web Socket
    $scope.$on("$destroy", function() {
        wsService.stopStompClient();
    });

    //Función para cerrar la sesión, borra el Local Storage y la cierra vía la API en el servidor
    $scope.cerrarSesion = function (){
        $localStorage.logged = false;
        $http.get("http://localhost:8080/logout-token?xauthtoken="+token)
        window.location.replace("/login.html");
    }


    //Formatea el tiempo a hh:mm:ss

    let formatearTiempo = function (horas){
        let segundos = 0;
        let minutos = 0;
        minutos = (horas - Math.floor(horas))*60;
        horas = Math.floor(horas);
        segundos = Math.floor( (minutos - Math.floor(minutos))*60);
        minutos = Math.floor(minutos);
        let tiempoFormateado = "";
        if(horas>=1)
            tiempoFormateado += horas+"h ";
        if(minutos>=1)
            tiempoFormateado+= minutos+"m ";
        tiempoFormateado+= segundos+"s";

        return tiempoFormateado;
    }

    //Calcula el tiempo estimado para que se complete el llenado
    $scope.calcularETA = function(pedido){
        let horas = (pedido.preset-pedido.masaAcumulada)/pedido.caudal;
        return formatearTiempo(horas);
    }
    //Calcula el tiempo que pasó desde que se comenzó a llenar
    $scope.calcularTiempoTranscurrido = function(pedido){
        if(pedido.estado==2){
            let fecha = pedido.fechaIProcesoCarga;
            var horas = (new Date().getTime() - new Date(fecha).getTime())/3600000;
            return formatearTiempo(horas);
        }
        else return 0;
    }
    //Acepto una alarma, y guardo en el servidor quien la aceptó y cuando
    $scope.aceptarAlarma = function(){
        let req = {
            method: 'POST',
            url: 'http://localhost:8080/api/v1/alarmas?xauthtoken='+token,
            headers : { 'Content-Type': 'application/json' },
            data: {"usuarioQueAcepto":{"id":$localStorage.userdata.idUser},"orden":{"nroOrden":$scope.nroOrden},"motivoAlarma":$scope.motivoAlarma}
        };
        $http(req).then(
            function(resp){
                if(resp.status===201) {
                    console.log("Alarma almacenada");
                }else{
                    console.log("Error al guardar la alarma.");
                }
            },
            function(respErr){
                console.log("Error al guardar la alarma.");
            }
        );
    }

    //Cargo todas las alarmas desde la API, muestro el div de mostrar alarmas, y oculto los otros
    $scope.cargarAlarmas = function (){
        $scope.mostrarAlarmas = true;
        $scope.mostrarConciliacion = false;
        $scope.mostrarPedidos = false;

        $http.get("http://localhost:8080/api/v1/alarmas?xauthtoken="+token).then(
            function(resp){
                $scope.alarmas=resp.data;
            },
            function(err){}
        );

    }

    //Adapto la fecha a DD/MM/AAAA

    $scope.adaptarFecha = function (fecha){
        if(fecha==undefined)
            return "";
        fecha = fecha.toString();
        //Si es un date traido de la BD
        if(fecha.includes("T")){
            let dias = fecha.split("T")[0];
            dias = dias.split("-")[2]+"/"+dias.split("-")[1]+"/"+dias.split("-")[0];
            horas=fecha.split("T")[1].split(".")[0];
            return dias+" "+horas;
        }
        //Si es un date del input type date
        else return fecha.split("-")[2]+"/"+fecha.split("-")[1]+"/"+fecha.split("-")[0];

    }

    //Función para ordenar la tabla
    $scope.ordenarPor = function (parametro){
        //Si ya está ordenada por ese parámetro, se la ordena de forma inversa agregando el "-" adelante del parámetro
        if($scope.orderby === parametro)
            $scope.orderby = "-" + parametro;
        else $scope.orderby = parametro;
    }


    //Filtro la tabla en base a los filtros aplicados en el front end
    $scope.filtrarTabla = function () {
        if (($scope.selectValue == "ID" || $scope.selectValue == "Estado") && document.getElementById("number").value == "")
            $scope.data = $scope.originalData;
        else if ($scope.selectValue == "ID")
            $scope.data = $scope.originalData.filter(pedido => pedido.nroOrden == document.getElementById("number").value);
        else if ($scope.selectValue == "Estado")
            $scope.data = $scope.originalData.filter(pedido => pedido.estado == document.getElementById("number").value);
        else if ($scope.selectValue == "Fecha de carga") {
            $scope.data = $scope.originalData.filter(pedido => $scope.adaptarFecha(pedido.fechaIProcesoCarga).includes($scope.adaptarFecha(document.getElementById("date").value)));
        }
        else if ($scope.selectValue == "Anulado") {
            console.log("Filtrando Anulado " + $scope.filtrarAnulado);
            if($scope.filtrarAnulado == "Anulado")
                $scope.data = $scope.originalData.filter(pedido => pedido.anulado == true);
            else if($scope.filtrarAnulado == "No anulado")
                $scope.data = $scope.originalData.filter(pedido => pedido.anulado == false);
        }

    }

    //Función para anular un pedido mediante la API
    $scope.anularPedido = function(nroOrden) {
        let req = {
            method: 'PUT',
            url: 'http://localhost:8080/api/v1/ordenes/anularOrden?xauthtoken=' + token + '&orden=' + nroOrden,
            headers: {'Content-Type': 'application/json'},
        };
        $http(req).then(
            function (resp) {
                if (resp.status === 200) {
                    $scope.cargarPedidos();
                    console.log("Orden anulada");
                } else {
                    console.log("Error al anular la orden con id " + nroOrden);
                }
            },
            function (respErr) {
                console.log("Error al anular");
            }
        );
    }

    //Función para remover filtros y restaurar los datos originales
    $scope.removerFiltros = function(){
        $scope.data = $scope.originalData;
    }

});
//Módulo encargado de gestionar la interacción con la API referida a los pedidos
moduloPedidos.factory('pedidosService',
    function($http, URL_API_BASE) {
        return {
            cargar: function(token) {
                console.log(URL_API_BASE + "ordenes?xauthtoken="+token)
                return $http.get(URL_API_BASE + "ordenes?xauthtoken="+token);
            },
            cargarConc: function(idPedido, token) {
                return $http.get(URL_API_BASE + "ordenes/conciliacion/id/" + idPedido + "?xauthtoken="+token);
            }
        }
    }
);

//Módulo encargado de gestionar el Web Socket
moduloPedidos.factory('wsService',
    function($rootScope, URL_WS, $timeout, $interval, $log, $localStorage) {

        var fnConfig = function(stomp, topic, cb) {
            $log.info("Stomp: suscribiendo a " + topic);
            stomp.subscribe(topic, function(payload, headers, res) {
                cb(payload, headers, res);
            });
        };
        return {
            initStompClient : function(topic, cb, stomp) {


                stomp.setDebug(function(args) {
                    $log.log(args);
                    if(stomp.sock.readyState > 1) {

                        $log.info("Intentando reconexión con WSocket");
                        fnConnect();
                    }
                });
                var fnConnect = function() {

                    if ($localStorage.logged && $localStorage.userdata) {
                        $log.log("iniciandoWS");

                        $log.log(URL_WS+"?xauthtoken="+$localStorage.userdata.authtoken);
                        stomp.connect(URL_WS+"?xauthtoken="+$localStorage.userdata.authtoken).then(function(frame) {
                            console.log("Stomp: conectado a " + URL_WS);
                            fnConfig(stomp, topic, cb);
                            //document.getElementById('seleccionarFiltro').options[1].selected = 'selected';
                        });
                    } else {
                        console.log("No existen credenciales para presentar en WS")
                    }
                };
                fnConnect();
            },
            stopStompClient: function() {
                if(stomp)
                    stomp.disconnect();
            }
        }

} );