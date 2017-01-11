app.service('areasService',function($http){
    var areas = [];
       var area = "";
       var $event = "";
 var changeArea = function(event,d) {
          $event =  event;
         return area = d;
    };  
    
    var areasService = {
         getAreas: function() {
      var promise = $http.get('/FIMDocumentos/FIMRest/hello/getAreas').then(function (response) {
      areas = response.data;
      });
      return promise;
    },
   
    getList: function() {
       return areas;
    },
    addToList : function(area){
          areas.unshift(area);
    },
    changeArea:function($event,d){
           
            changeArea($event,d);
    },
      updateAreaDialog:function($event,d){
          
            changeArea($event,d);
    },
      createArea: function(area) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/createArea",
    data: JSON.stringify(area)
}).then(function(result){
    
            return result.data;
        });  },
    
    getArea: function() {
        var d = area;
        area = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return areasService;
    });

