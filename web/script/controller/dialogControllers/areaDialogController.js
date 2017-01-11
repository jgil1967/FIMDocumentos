  app.controller('areaDialogController',['$scope','$http','$timeout', '$q','areasService','$mdDialog', 
      function($scope,$http,$timeout, $q,areasService,$mdDialog) {
         
          $scope.$watch(function(){return areasService.getArea();}, function (area) {
                 
                 
              if (area != ""){
                  
                    
                   
                     if (area.id == 0){
       
        $mdDialog.show({
        controller: areaDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'areaDialog.tmpl.html',
        parent: angular.element(area.body),
        targetEvent: areasService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        area:  area ,
        
        update: false
     }
      });
                      } else {
                   // window.console.log("Es update");
                    $mdDialog.show({
                        controller: areaDialogController,
                        controllerAs: 'ctrl',
                        templateUrl: 'areaDialog.tmpl.html',
                        parent: angular.element(area.body),
                        targetEvent: areasService.getEvent(),
                        clickOutsideToClose: true,
                        locals: {
                            area: area,
                            
                            update: true
                        }
                    });
                }
                  
                   
                
                  
          
              }
          },true);
          
          function areaDialogController($scope,$http,$timeout, $q,areasService,area,update)
        {
    //Obtener los que si y los que no
    
    
              $scope.update = update;
            if ($scope.update == true) {
            }
            $scope.area  = area;
             $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
  
              $scope.nuevoArea = function(){
              areasService.createArea($scope.area).then(function (data) {
                  areasService.addToList(data);
                            $mdDialog.hide();
                        });
        
              };
    
        }
              }]);
          
          
  

