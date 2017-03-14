app.service('databaseBackupService',function($http){
  
    var databaseBackupService = {
       
      createDatabaseBackup: function(databaseBackupData) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/createDatabaseBackup",
    data: JSON.stringify(databaseBackupData)
}).then(function(result){
    
            return result.data;
        });  }
  
  };
      return databaseBackupService;
    });

