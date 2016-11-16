app.controller('documentosController',['$scope','topBannerService','documentosService',
     function ($scope,topBannerService,documentosService)
    { 
        
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
              window.console.log(JSON.stringify($scope.document));
              
             documentosService.downloadDocument($scope.document).then(function (data) {
                          
                        });
            
                
         };
        
         $scope.updateDocument = function ($event,document){
       documentosService.updateDocumentDialog($event,document);
   }; 
        
        
          topBannerService.setTitle("Documentos");
            $scope.createDocument = function ($event){
             $scope.document = { id:0, name:'', description:'', color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
            };
               $scope.documents = [];
              
                 $scope.getDocuments = function (){
                  documentosService.getDocuments().then(function(d) {
                   $scope.documents = documentosService.getList();
                // window.console.log("Documents : " +  JSON.stringify($scope.documents));
                });
                 };
                   
           $scope.getDocuments();  
            
            
            
            
    }

]);