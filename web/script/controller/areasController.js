app.controller('areasController',['$http','$scope','topBannerService','areasService',
     function ($http,$scope,topBannerService,areasService)
    { 
        
topBannerService.setTitle("Areas");

$scope.updateArea = function ($event,area){
            
            areasService.updateAreaDialog($event,area);
}

$scope.areas = []
  $scope.getAreas = function (){
                  areasService.getAreas().then(function(d) {
                   $scope.areas = areasService.getList();
              
                });
                 };
                   
        $scope.getAreas();  
  $scope.createArea = function ($event){
             $scope.area = { id:0, name:'' , createdBy: $("#idUsuario").val()};
             
              areasService.updateAreaDialog($event,$scope.area );
              
            };
            
   }

]);