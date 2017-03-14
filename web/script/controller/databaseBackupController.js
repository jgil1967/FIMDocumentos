//subidospormiController
  app.controller('databaseBackupController',['$scope','databaseBackupService','topBannerService',
      function($scope,databaseBackupService,topBannerService) {
topBannerService.setTitle("Respaldo de base de datos");
            $scope.databaseBackupData = {databaseName:"FIMDocumentos",user:"postgres",password:"postgresql1",postgresqlPath:"/Library/PostgreSQL/9.6/bin/",email:"jgil1967@hotmail.com",nombreBackup:"FIMDocumentos"};  
            $scope.listoParaDescargar = false;
            $scope.createDatabaseBackupData = function (){
               databaseBackupService.createDatabaseBackup($scope.databaseBackupData).then(function (data) {
                 $scope.listoParaDescargar = true; 
               });
            
            }
               
    }]);