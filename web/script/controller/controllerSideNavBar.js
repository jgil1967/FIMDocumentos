app.controller('controllerSideNavBar', ['$scope','contentsService','$timeout', '$q', '$log','objectsService','$location','usuariosService',
    function ($scope,contentsService,$timeout, $q, $log,objectsService,$location,usuariosService)
    
    {        $scope.dDto = {
                    query : ""
                };
                $scope.opciones = [];
                setTimeout(function(){ $scope.loggedUser = usuariosService.getLoggedUser(); 
                 if ($scope.loggedUser.root==true){
                       $scope.opciones = contentsService.getContentsRoot();
                 }
                    if ($scope.loggedUser.isAdministrator==true && $scope.loggedUser.root==false){
                     
                     $scope.opciones = contentsService.getContentsAdministrator();
                 }
                 if ($scope.loggedUser.isAdministrator==false && $scope.loggedUser.root==false){
                     
                     $scope.opciones = contentsService.getContentsNormal();
                 }
                 
                }, 100);
                
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
        
         
    }]);