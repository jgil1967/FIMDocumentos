app.controller('documentosController',['$http','$scope','topBannerService','documentosService','areasService','usuariosService',
     function ($http,$scope,topBannerService,documentosService,areasService,usuariosService)
    { 

      $scope.areas= [];
       $scope.selectedAreas= [];
              $scope.usuarios= [];
       $scope.selectedUsuarios= []; 
                $scope.filteredDocuments = function () {
    return $scope.documents.filter(function (document) {
      return $scope.selectedAreas.indexOf(document.idArea) !== -1 && $scope.selectedUsuarios.indexOf(document.createdBy) !== -1;
    });
  };
              
   areasService.getAreas().then(function(d) {
                   $scope.areas= areasService.getList();
                   
                   usuariosService.getUsuarios().then(function(d) {
                       $scope.usuarios = usuariosService.getList();
                       
                       angular.forEach($scope.usuarios, function (usuario, key) {
                       $scope.selectedUsuarios.push(usuario.id);
                   });
                        angular.forEach($scope.areas, function (area, key) {
                       $scope.selectedAreas.push(area.id);
                   });
                   
                   $scope.getDocuments();  
                   
                   
                           });  
                   
                  
                
               });  
       
       
       
               $scope.options = {
    rowSelection: false, multiSelect: false, autoSelect: false,decapitate: false, largeEditDialog: false,  boundaryLinks: false,
    limitSelect: true, pageSelect: true};


            $scope.limitOptions = [5, 10, 15, {
    label: 'All',
    value: function () {
      return $scope.documents ? $scope.documents.count : 0;
    }
  }];
          $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };
        
        
         $scope.downloadDocument = function (document){
              $scope.document = document;
             // window.console.log(JSON.stringify($scope.document));
              
             documentosService.downloadDocument($scope.document).then(function (data) {
                          
                        });
            
                
         };
        
         $scope.updateDocument = function ($event,document){
       documentosService.updateDocumentDialog($event,document);
   }; 
        
         
          topBannerService.setTitle("Documentos");
            $scope.createDocument = function ($event){
             $scope.document = { id:0, fileDate:new Date(), name:'', description:'',idArea:0,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
            };
               $scope.documents = [];
              
              $scope.dateRangeFilter = function (property, startDate, endDate) {
                  //change  start date to 00:00:00
    return function (item) {
//                window.console.log(item);
//                window.console.log(startDate);
//                window.console.log(endDate);
        if (item[property] === null) return false;
 
        var itemDate = moment(item[property]);
        var s = moment(startDate, "MM-DD-YYYY");
        var e = moment(endDate, "MM-DD-YYYY");
 
        if (itemDate >= s && itemDate <= e) return true;
        return false;
    }
    
}
     $scope.fileDateRangeFilter = function (property, startDate, endDate) {
                  //change  start date to 00:00:00
    return function (item) {
//                window.console.log(item);
//                window.console.log(startDate);
//                window.console.log(endDate);
        if (item[property] === null) return false;
 
        var itemDate = moment(item[property]);
        var s = moment(startDate, "MM-DD-YYYY");
        var e = moment(endDate, "MM-DD-YYYY");
 
        if (itemDate >= s && itemDate <= e) return true;
        return false;
    }
   
}

 $scope.tags = [];
    var countries = [];
    
  $scope.loadCountries = function($query) {
    return $http.get('/FIMDocumentos/FIMRest/hello/getKeywords', { cache: false}).then(function(response) {
      countries = response.data;
      return countries.filter(function(country) {
        return country.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
      });
    });
  };

$scope.dates = {
    startDate:'',
    endDate:'',
    startFileDate:'',
    endFileDate:''
} 

//         $scope.$watch("dates", function(newValues, oldValues, scope) {
//             window.console.log(newValues);
//            window.console.log(JSON.stringify($scope.dates ));
//});


$scope.search = function ($event){
         //   window.console.log("Search");
                if ($scope.searchDocumentos !="" || new Date($scope.startDate) != "Invalid Date" || new Date($scope.endDate) != "Invalid Date" ||new Date( $scope.startFileDate) != "Invalid Date" ||new Date($scope.endFileDate) != "Invalid Date" )
            {
                  var dates2 = {
                oldestCreatedOn: $scope.startDate,
                newestCreatedOn: $scope.endDate,
                oldestFileDate: $scope.startFileDate,
                newestFileDate: $scope.endFileDate
                    } 
                    var filters = {
                        dates:dates2,
                        keywords:$scope.tags,
                        filterQuery: $scope.searchDocumentos
                    }
                documentosService.getDocumentsFilters(filters).then(function() {
                $scope.documents = documentosService.getList();
            });
            }
            
}
 $scope.$watchGroup(['startDate', 'endDate', 'startFileDate', 'endFileDate'], function(newValues, oldValues, scope) {
           
  
},true);




                 $scope.getDocuments = function (){
                  documentosService.getDocuments().then(function(d) {

                      
                   $scope.documents = documentosService.getList();
                   window.console.log($scope.documents);
                   
                  
                   
                });
                 };
                   
           
            
            
            
            
    }

]);