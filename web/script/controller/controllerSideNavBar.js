app.controller('controllerSideNavBar', ['$scope','contentsService','$timeout', '$q', '$log','objectsService','$location',
    function ($scope,contentsService,$timeout, $q, $log,objectsService,$location)
    {        $scope.dDto = {
                    query : ""
                };
                
            $scope.$watch('dDto.query', function() {
                if (  $scope.dDto.query != ""){
                    objectsService.searchObjects($scope.dDto).then(function(searchResults) {
                  
                        
                    });
                }
                else{
                       $location.path('/');
                }
                  
            
            });
       /////////////////////////////////////////////////////////////////////////
        $scope.opciones = [];
          $scope.obtenerOpciones = function (){
                $scope.opciones = contentsService.getContents();
         };
        $scope.obtenerOpciones();
    }]);