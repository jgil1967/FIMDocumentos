app.controller('usuariosController',['$scope','topBannerService','usuariosService',
     function ($scope,topBannerService,usuariosService)
    { 
        
           
               $scope.options = {
    rowSelection: false, multiSelect: false, autoSelect: false,decapitate: false, largeEditDialog: false,  boundaryLinks: false,
    limitSelect: true, pageSelect: true};


            $scope.limitOptions = [5, 10, 15, {
    label: 'All',
    value: function () {
      return $scope.usuarios ? $scope.usuarios.count : 0;
    }
  }];
          $scope.query = {
    order: 'name',
    limit: 50,
    page: 1
  };
        
        
         
        
         $scope.updateUser = function ($event,usuario){
       usuariosService.updateUsuarioDialog($event,usuario);
   }; 
        
        
          topBannerService.setTitle("Usuarios");
            $scope.createUsuario = function ($event){
             $scope.usuario = { id:0, name:'', description:'',contrasena:'',availability:true ,idArea:0, createdBy: $("#idUsuario").val()};
             
              usuariosService.updateUsuarioDialog($event,$scope.usuario );
              
            };
               $scope.users = [];
              
                 $scope.getUsuarios = function (){
                  usuariosService.getUsuarios().then(function(d) {
                   $scope.users = usuariosService.getList();
             // window.console.log("Usuarios : " +  JSON.stringify($scope.usuarios));
                });
                 };
                   
        $scope.getUsuarios();  
            
            
            
            
    }

]);