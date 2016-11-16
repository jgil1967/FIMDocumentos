/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module('appApp', ['ngRoute','ngMaterial', 'ngMessages','md.data.table','ngTagsInput'])
.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue')
    .accentPalette('blue');
});

app.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : '/FIMDocumentos/pages/documentos.html',
                controllerAs: 'ctrl',
                controller  : 'documentosController'
            })
            
            .when('/documentos', {
                templateUrl : '/FIMDocumentos/pages/documentos.html',
                controllerAs: 'ctrl',
                controller  : 'documentosController'
            })
             .when('/documentos/:id', {
                templateUrl : '/FIMDocumentos/pages/documento.html',
                controller  : 'documentoController'
            })
            .when('/searchResults/:searchTerm', {
                templateUrl : '/FIMDocumentos/pages/searchResults.html',
                controller  : 'searchController'
            })
          
    });



     app.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);
     
     
app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileAndFieldsToUrl = function(file, fields, uploadUrl){
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
                       return true;
        })
        .error(function(){
           return false;
        });
    }
}]);



