  app.controller('topBannerController',['$scope','topBannerService', function($scope,topBannerService) {

        // create a message to display in our view
        $scope.title = "";
        $scope.$watch(function(){return topBannerService.getTitle();}, function (t) {
            if (t!=""){
                 $scope.title = t;
                 //window.console.log( $scope.message);
            }
            }, true);
    }]);