app.service('usuariosService',function($http){
    var usuarios = [];
       var usuario = "";
       var $event = "";
 var changeUsuario = function(event,d) {
          $event =  event;
         return usuario = d;
    };  
    
    var usuariosService = {
         getUsuarios: function() {
      var promise = $http.get('/FIMDocumentos/FIMRest/hello/getUsuarios').then(function (response) {
      usuarios = response.data;
      });
      return promise;
    },
   
    getList: function() {
       return usuarios;
    },
    addToList : function(usuario){
          usuarios.unshift(usuario);
    },
    changeUsuario:function($event,d){
           
            changeUsuario($event,d);
    },
      updateUsuarioDialog:function($event,d){
          
            changeUsuario($event,d);
    },
    updateUser: function(usuario) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/updateUsuario",
    data: JSON.stringify(usuario)
}).then(function(result){
    
            return result.data;
        });  },
      createUsuario: function(usuario) {
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/createUsuario",
    data: JSON.stringify(usuario)
}).then(function(result){
    
            return result.data;
        });  },
     verificaDisponibilidadUsuario: function(usuario) {
         //   window.console.log("verificaDisponibilidadUsuario");
 return  $http({
    method: 'POST',
    url: "/FIMDocumentos/FIMRest/hello/verificaDisponibilidadUsuario",
    data: JSON.stringify(usuario)
}).then(function(result){
    
            return result.data;
        });  },
    getUsuario: function() {
        var d = usuario;
        usuario = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return usuariosService;
    });

