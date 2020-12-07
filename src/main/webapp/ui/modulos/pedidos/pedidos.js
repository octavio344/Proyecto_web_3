let moduloPedidos=angular.module('pedidos',[])

.constant('URL_API_BASE', 'http://localhost:8080/api/v1/')
.constant('URL_BASE', 'http://localhost:8080/')

moduloPedidos.controller('pedidosController', function($scope, $rootScope, pedidosService){

    $scope.titulo = "Pedidos realizados:";

    $scope.pedidosPorPagina = 5;

    $scope.cargarPedidos = function (){
        pedidosService.cargar().then(
            function(resp){
                $scope.data=resp.data;
                $scope.totalDeItems = $scope.data.length;
            },
            function(err){}
        );
    }

    $scope.cargarPedidos();

});

moduloPedidos.factory('pedidosService',
    function($http, URL_API_BASE) {
        return {
            cargar: function() {
                return $http.get(URL_API_BASE + "ordenes");
            }
        }
    }
);