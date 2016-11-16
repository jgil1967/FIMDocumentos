  app.controller('documentDialogController',['$scope','$http','$timeout', '$q','documentosService','$mdDialog', 'fileUpload','tagsService','documentKeywordRelationshipService', 
      function($scope,$http,$timeout, $q,documentosService,$mdDialog, fileUpload,tagsService,documentKeywordRelationshipService) {
         
          $scope.$watch(function(){return documentosService.getDocument();}, function (document) {
             
              if (document != ""){
        if (document.id == 0){
       
        $mdDialog.show({
        controller: documentDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'documentDialog.tmpl.html',
        parent: angular.element(document.body),
        targetEvent: documentosService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        document:  document ,
        update: false,
        fileUpload:fileUpload
     }
      });
                      } else {
                   // window.console.log("Es update");
                    $mdDialog.show({
                        controller: documentDialogController,
                        controllerAs: 'ctrl',
                        templateUrl: 'documentDialog.tmpl.html',
                        parent: angular.element(document.body),
                        targetEvent: documentosService.getEvent(),
                        clickOutsideToClose: true,
                        locals: {
                            document: document,
                            update: true,
                            fileUpload: fileUpload
                        }
                    });
                }
                      
              }
          },true);
          
          function documentDialogController($scope,$http,$timeout, $q,documentosService,document,update,fileUpload,tagsService,documentKeywordRelationshipService)
        {
            
              $scope.update = update;
            $scope.tags = [];
            $scope.tagsOriginales = [];
            if ($scope.update == true) {
              //  window.console.log(JSON.stringify(document.keywords));
                $scope.tags = document.keywords;
                $scope.tagsOriginales = $scope.tags;
            }


   var countries = [];
   
      $scope.getTags = function (){
                  tagsService.getTags().then(function(d) {
                  countries= tagsService.getList();
                });
                 };
                   
           $scope.getTags();  
   
   
  $scope.loadCountries = function($query) {
    return $http.get('/FIMDocumentos/FIMRest/hello/getKeywords', { cache: false}).then(function(response) {
      countries = response.data;
      return countries.filter(function(country) {
        return country.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
      });
    });
  };
  
   $scope.viewTags = function (){
      // console.log(JSON.stringify($scope.tags));
  
   }
  
            $scope.document  = document;
             $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
    $scope.tagsStuff = function (){
                         angular.forEach($scope.tags, function (tag, key) {
                                    if ("id" in tag) {
                                        $scope.dDto = {
                                            idKeyword: tag.id,
                                            idDocument: $scope.document.id
                                        };
                                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                           // console.log(JSON.stringify(data));

                                        });

                                    } else {
                                        tagsService.createTag(tag).then(function (data) {
                                            $scope.dDto = {
                                                idKeyword: data.id,
                                                idDocument: $scope.document.id
                                            };

                                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                               // console.log(JSON.stringify(data));

                                            });
                                            countries.push($scope.dDto);
                                        });

                                    }
                                });
            }


  $scope.editDocument = function () {
                
                
                 documentKeywordRelationshipService.deleteDocumentKeywordRelationshipsByDocument($scope.document).then(function (data) {    
                   // window.console.log("Se han borrado las relaciones de este documento");
                 });
                 $scope.document.keywords = $scope.tags;
                 $scope.tagsStuff();
                 
                                documentosService.updateDocument($scope.document).then(function (data) {
                                $mdDialog.hide();
                 
                            });
                 
                 
              
            }

              $scope.nuevoDocument = function(){
               //   window.console.log(JSON.stringify(  $scope.document));
                 var file = $scope.myFile;
               console.dir($scope.myFile);
               console.dir($scope.myFile.name);
               $scope.document.filename = $scope.myFile.name;
       var uploadUrl = "/FIMDocumentos/FIMRest/hello/upload";
        var fields = [{"name": "name", "data": document.name},{"name": "description", "data": document.description}];
    // var resp = fileUpload.uploadFileAndFieldsToUrl(file, fields, uploadUrl);
            //    window.console.log( "document: " + JSON.stringify(document));
          var fd = new FormData();
        fd.append('file', file);
        for(var i = 0; i < fields.length; i++){
            fd.append(fields[i].name, fields[i].data)
        }
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
             documentosService.createDocument($scope.document).then(function (data) {
                            $mdDialog.hide();
                            $scope.document = data;
                            
angular.forEach($scope.tags, function(tag, key) {
if("id" in tag) {
    $scope.dDto = {
        idKeyword:tag.id,
        idDocument:$scope.document.id
    };
     documentKeywordRelationshipService.createdocumentKeywordRelationship(  $scope.dDto).then(function (data) {
                     //     console.log(JSON.stringify(data));
                          
                        });
  
}
else{
     
      tagsService.createTag(tag).then(function (data) {
                           $scope.dDto = {
        idKeyword:data.id,
        idDocument:$scope.document.id
    };
    
        documentKeywordRelationshipService.createdocumentKeywordRelationship(  $scope.dDto).then(function (data) {
                       //   console.log(JSON.stringify(data));
                          
                        });
    countries.push( $scope.dDto);
                        });
    
}
            });
                             $scope.document.keywords =    $scope.tags;
                            documentosService.addToList($scope.document);
                        });
            
            
    
           
      
        })
        .error(function(){
           window.console.log("false");
        });
        
        
              };
    
        }
              }]);
          
          
  

