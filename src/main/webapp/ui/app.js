let moduloPedidos=angular.module('final-iw3',['ngStorage', 'ngStomp'])

    .constant('URL_API_BASE', 'http://localhost:8080/api/v1/')
    .constant('URL_BASE', 'http://localhost:8080/')
    .constant('URL_WS', '/api/v1/ws')


moduloPedidos.controller('pedidosController', function($scope, $rootScope, $timeout, $interval, $log, $localStorage, pedidosService, wsService, $stomp){


    if($localStorage.logged!=true)
        window.location.replace("/login.html");

    $rootScope.stomp = $stomp;


    $scope.titulo = "Pedidos realizados:";

    $scope.pedidosPorPagina = 5;

    $scope.mostrarPedidos = true;
    $scope.mostrarConciliacion = false;
    $scope.idConciliacion = 0;
    let token = $localStorage.userdata.authtoken;
    $scope.cargarPedidos = function (){
        pedidosService.cargar(token).then(
            function(resp){
                $scope.data=resp.data;
                $scope.totalDeItems = $scope.data.length;
            },
            function(err){}
        );

    }

    $scope.mostrarPaginaPedidos = function(){
        $scope.mostrarPedidos = true;
        $scope.mostrarConciliacion = false;
    }

    $scope.cargarConciliacion = function (idPedido){
        pedidosService.cargarConc(idPedido, token).then(
            function(resp){
                $scope.mostrarPedidos = false;
                $scope.mostrarConciliacion = true;
                console.log($scope.mostrarConciliacion);
                $scope.idConciliacion = idPedido;
                $scope.dataConc=resp.data;
            },
            function(err){}
        );
}

    $scope.cargarPedidos();

    $scope.iniciaWS = function() {
        wsService.initStompClient('/iw3/data', function(payload,
                                                                 headers, res) {
            //console.log(payload);
            //$log.log(payload);
            //$scope.notificar(payload.payload.label,payload.payload.value);
            //$scope.$apply();
        }, $scope.stomp);
    }

   $scope.iniciaWS();


    $scope.$on("$destroy", function() {
        wsService.stopStompClient();
    });


    $scope.cerrarSesion = function (){
        $localStorage.logged = false;
        window.location.replace("/login.html");
    }
});

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