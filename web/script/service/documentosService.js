app.service('documentosService',function($http){
    var documents = [];
    var dates = {};
       var document = "";
       var $event = "";
 var changeDocument = function(event,d) {
          $event =  event;
         return document = d;
    };  
    
    var documentosService = {
         getDocuments: function() {
      var promise = $http.get('/FIMDocumentos/FIMRest/hello/getDocuments').then(function (response) {
      documents = response.data;
      });
      return promise;
    },
     getDatesDTO: function() {
      var promise = $http.get('/FIMDocumentos/FIMRest/hello/getDatesDTO').then(function (response) {
      dates = response.data;
      });
      return promise;
    },
    getList: function() {
       return documents;
    },
    getDates: function() {
       return dates;
    },
    addToList : function(document){
          documents.unshift(document);
    },
    updateDocumentDialog:function($event,d){
           
            changeDocument($event,d);
    },
    downloadDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/downloadDocument",
    data: JSON.stringify(document)
}).then(function(result){
    window.console.dir(result);
                window.console.log(result);
          // return result.data;
        });  },
     updateDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/updateDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
      createDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/createDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
    
    getDocument: function() {
        var d = document;
        document = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return documentosService;
    });

