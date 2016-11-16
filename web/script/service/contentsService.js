app.service('contentsService', function()
{
       var contents = [
              {name: "Documentos",url:"documentos"}/*,
              {name: "Búsqueda",url:"busqueda"}/*,
              {name: "Tablas",url:"tablas"},
              {name: "Servidores",url:"servidores"}*/
          ];
  var contentsService = {
   
    getContents :function (){
        return contents;
    }
  };
  return contentsService;
   
});


