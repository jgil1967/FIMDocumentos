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
             $scope.document = { id:0, fileDate:new Date(), name:'', description:'', color:"#01579b",kind:"document" };
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
 


                 $scope.getDocuments = function (){
                  documentosService.getDocuments().then(function(d) {
                      documentosService.getDatesDTO().then(function(d) {
                          $scope.dates = documentosService.getDates();
                          //window.console.log( $scope.dates );
                          
                              var fulldate = new Date ($scope.dates.oldestCreatedOn).setHours(00,00,00);
                     var converted_date = moment(fulldate).format("");
                   $scope.startDate =  new Date (converted_date);
                   
                   
                   $scope.endDate=new Date ( $scope.dates.newestCreatedOn);
                     /////////////////////////////////////////////////////////////////////////////////////
                      fulldate = new Date ($scope.dates.oldestFileDate).setHours(00,00,00);
                      converted_date = moment(fulldate).format("");
                   $scope.startFileDate =  new Date (converted_date);
                   
                   
                   $scope.endFileDate=new Date (  $scope.dates.newestFileDate);
                   
                   if ( $scope.startFileDate > $scope.startDate ){
                        
                       $scope.startFileDate= $scope.startDate;
                   }
                   else{
                        
                        $scope.startDate= $scope.startFileDate;
                   }
                   
                          
                      });
                      
                   $scope.documents = documentosService.getList();
                   
                   
                  
                   
                });
                 };
                   
           $scope.getDocuments();  
            
            
            
            
    }

]);