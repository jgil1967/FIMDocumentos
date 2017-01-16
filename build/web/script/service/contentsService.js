app.service('contentsService', function()
{
       var contentsRoot = [
              {name: "Documentos",url:"documentos"},
              {name: "Areas",url:"areas"},
              {name: "Usuarios",url:"usuarios"},
              {name: "Palabras clave",url:"keywords"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
            var contentsAdministrator = [
              {name: "Documentos",url:"documentos"},
              {name: "Usuarios",url:"usuarios"},
              {name: "Palabras clave",url:"keywords"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
            var contentsNormal = [
              {name: "Documentos",url:"documentos"},
              {name: "Palabras clave",url:"keywords"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
  var contentsService = {
   
    getContentsRoot :function (){
        return contentsRoot;
    },
     getContentsAdministrator :function (){
        return contentsAdministrator;
    },
     getContentsNormal :function (){
        return contentsNormal;
    }
  };
  return contentsService;
   
});


