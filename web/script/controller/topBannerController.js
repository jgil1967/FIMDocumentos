  app.controller('topBannerController',['$scope','topBannerService','usuariosService',
      function($scope,topBannerService,usuariosService) {

        //window.console.log("idUsuario : " + $("#idUsuario").val());
        var user = {id: $("#idUsuario").val()};
         usuariosService.getUsuarioByID(user).then(function(d){
           $scope.loggedUser = usuariosService.getLoggedUser();
           
        });
        $scope.loggedUser = {};
        $scope.title = "";
        $scope.$watch(function(){return topBannerService.getTitle();}, function (t) {
            if (t!=""){
                 $scope.title = t;
                 
            }
            }, true);
    }]);