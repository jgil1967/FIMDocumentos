<%-- 
    Document   : index
    Created on : Sep 29, 2016, 2:51:01 PM
    Author     : jonathangil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Documentos FIM</title>
          <link href="style/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/prism.css" rel="stylesheet">
    <link href="css/ghpages-materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="css/angular-material.min.css" rel="stylesheet" type="text/css"/>
    <link href="icons/icon.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/flaticon.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/styleArticle.css" rel="stylesheet" type="text/css"/>
    <link href="script/shared/dist/md-data-table.min.css" rel="stylesheet" type="text/css"/>
    <link href="script/ng-tags-input.min/ng-tags-input.min.css" rel="stylesheet" type="text/css"/>
    
    
      <script src="script/shared/jquery-1.12.0.min.js" type="text/javascript"></script>
       <script src="js/jquery.timeago.min.js"></script>
    <script src="js/prism.js"></script>
    <script src="js/materialize.min.js" type="text/javascript"></script>
    <script src="js/init.js"></script>
      <script src="script/shared/angular.min.js" type="text/javascript"></script>
      <script src="script/shared/moment.js" type="text/javascript"></script>
    <script src="script/ng-tags-input.min/ng-tags-input.min.js" type="text/javascript"></script>
      <script src="script/shared/angular-route.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-animate.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-aria.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-messages.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-material.min.js" type="text/javascript"></script>
       <script src="script/shared/dist/md-data-table.min.js" type="text/javascript"></script>
  
      <script src="script/app.js" type="text/javascript"></script>
      <script src="script/service/contentsService.js" type="text/javascript"></script>
      <script src="script/service/objectsService.js" type="text/javascript"></script>
      <script src="script/service/documentosService.js" type="text/javascript"></script>
      <script src="script/service/topBannerService.js" type="text/javascript"></script>
      <script src="script/service/tagsService.js" type="text/javascript"></script>
      <script src="script/service/documentKeywordRelationshipService.js" type="text/javascript"></script>
      <script src="script/controller/appController.js" type="text/javascript"></script>
      <script src="script/controller/controllerSideNavBar.js" type="text/javascript"></script>
      <script src="script/controller/topBannerController.js" type="text/javascript"></script>
      <script src="script/controller/documentosController.js" type="text/javascript"></script>
      
      <script src="script/controller/searchController.js" type="text/javascript"></script>
    <!--Dialog controllers -->
    <script src="script/controller/dialogControllers/documentDialogController.js" type="text/javascript"></script>
    <!--Dialog controllers -->
    </head>
    <!--gradient wellText-->
    <body class=" " ng-app="appApp">
        <div ng-controller="documentDialogController"></div>
        <div ng-controller="searchController"></div>
   <script type="text/ng-template" id="tag-template">
      <div class="tag-template">
        
        <div class="right-panel">
          <span>{{$getDisplayText()}}</span>
          <a class="remove-button" ng-click="$removeTag()">&#10006;</a>
        </div>
      </div>
    </script>
    
    <script type="text/ng-template" id="autocomplete-template">
      <div class="autocomplete-template">
        
        <div class="right-panel">
          <span ng-bind-html="$highlight($getDisplayText())"></span>
          
           
        </div>
      </div>
    </script>
          <script type="text/ng-template" id="documentDialog.tmpl.html">
   <md-dialog aria-label="Documento" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update">Nuevo documento</h2>
         <h2 ng-if="update">Editar documento</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formDocument">
        <!-------------------------------------------->
<div ng-hide="update"> 
 <p>
		Seleccione un archivo : <input required type="file" file-Model="myFile" name="file" size="45" />
	   </p>
   </div>
   </br>
   
        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="name" ng-model="document.name" type="text" class="validate">
        <label for="name">Nombre (campo obligatorio) </label>
        </div>
<!-------------------------------------------->
        <div class="input-field col ">
        <label  ng-if="!update" for="description">Descripción  (campo obligatorio)</label>
        <textarea  id="description" ng-model="document.description"   class="materialize-textarea" class="validate" required></textarea>

        </div>
<!--document.fileDateDate -->
  <div class="input-field col ">
        <label  ng-if="!update" for="date"></label>
        <input required  id="date" ng-model="document.fileDate" type="date" class="validate">

        </div>
<!-------------------------------------------->
 <br>
 <p><strong>Palabras clave</strong></p>
 <tags-input ng-model="tags" 
                display-property="name" 
                placeholder="Palabras clave" 
                replace-spaces-with-dashes="false"
                template="tag-template">
      <auto-complete source="loadCountries($query)"
                     min-length="0"
                     load-on-focus="true"
                     load-on-empty="true"
                     max-results-to-show="32"
                     template="autocomplete-template"></auto-complete>
    </tags-input>
    
 
       <br>
        <!-------------------------------------------->
            
    <!-- <h5>File Upload with Jersey</h5>

	<form action="api/hello/upload" method="post" enctype="multipart/form-data">
 -->
	  

	 <!--  <input type="submit" value="Upload It" /> -->
	 <!-- </form> -->
        <!-------------------------------------------->
     
           <br>
    

       <br>
           <input type="color" name="color1" id="color1" ng-model="document.color"></br>
      <label>Color :   {{document.color}}</label> 
       
           <!-------------------------------------------->
         
          
        <!-------------------------------------------->
        </form>
        </div>
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="formDocument.$invalid" class="btn waves-effect waves-light" type="submit" name="action"  ng-click="nuevoDocument()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="formDocument.$invalid" class="btn waves-effect waves-light" type="submit" name="action"  ng-click="editDocument()">Editar
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
        <header>
      <div class="container">
    <a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-paquetexpress circle hide-on-large-only"><i class="material-icons">menu</i></a>
      </div>
        <ul id="nav-mobile" class="side-nav fixed" ng-controller="controllerSideNavBar as ctrl">
        <li class="logo">
            <a id="logo-container" href="#" class="brand-logo">
                <img id="front-page-logo"  src="img/uas.png" alt=""/>
<!--                <object id="front-page-logo" class="svg-class" type="image/svg+xml" data="img/uas.svg"></object>-->
           </a> 
        </li>
        <li class="search">
        
             
                  <!--------------------------------------------------------------------------------------->    
 <md-input-container class="md-icon-float md-block">
      <!-- Use floating label instead of placeholder -->
<!--      <input placeholder="Búsqueda" ng-model="dDto.query" type="text">-->
    </md-input-container>
              <!--------------------------------------------------------------------------------------->
           
        </li>
          <!--  ng-href="#/{{opcion.url}}"  -->
          <div style="margin-top: 20px">
               <div  ng-repeat="opcion in opciones"> 
              
        <li class="bold"><a ng-href="#/{{opcion.url}}" class="waves-effect waves-paquetexpress">{{opcion.name}}</a></li>
        </div>
          </div>
         
</ul>
        
    </header>
            <main>     
        
        
        <div class="section no-pad-bot" id="index-banner" ng-controller="topBannerController">
        <div class="container">
                  <div class='row center'>
        <!--   <h4 class ="header col s12 light center">A modern responsive front-end framework based on Material Design</h4>
         --> </div>
            
         <h4 class="header">Repositorio de documentos - FIM</h4> 
          <h5 class="header">{{title}}</h5> 
             </div>
          <br>

        </div>
     
      </div>
  <div id="main">
      <div class="container">
        <div class="section">
       
            <div ng-view autoscroll="true">
                
            </div>
      <!-- #01579b -->
          <div class="row">
            <div class="col s12 m8 offset-m2">
              <br>
              </div>
          </div>

          <div class="row">
            </div>

          <!--   Promo Section   -->
          <div class="row">
            <div class="col s12 m4">
              <div class="center promo">
              </div>
            </div>

            <div class="col s12 m4">
              <div class="center promo">
                  </div>
            </div>

            <div class="col s12 m4">
              <div class="center promo">
                </div>
            </div>
          </div>

        </div>
        <div class="divider"></div>
        <div class="section">
          <div class="row center">
          </div>
        </div>
      </div>
  </div>

    </main>    <footer class="page-footer">
      <div class="container">
        <div class="row">
          <div class="col l4 s12">
         
          </div>
          <div class="col l4 s12">
              </div>
          <div class="col l4 s12" style="overflow: hidden;">
               </div>
        </div>
      </div>
      <div class="footer-copyright">
        <div class="container">
       </div>
      </div>
    </footer>

    
        </div>
    </body>
</html>
