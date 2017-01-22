app.controller('documentDialogController', ['$scope', '$http', '$filter', '$timeout', '$q', 'documentosService', '$mdDialog', 'fileUpload', 'tagsService', 'documentKeywordRelationshipService', 'areasService', 'usuariosService',
    function ($scope, $http, $filter, $timeout, $q, documentosService, $mdDialog, fileUpload, tagsService, documentKeywordRelationshipService, areasService, usuariosService) {

        $scope.$watch(function () {
            return documentosService.getDocument();
        }, function (document) {

            if (usuariosService.getLoggedUser() != null) {
                // window.console.log("Hello");
                $scope.loggedUser = usuariosService.getLoggedUser();
                // window.console.log(JSON.stringify($scope.loggedUser));
                var area = {id: $scope.loggedUser.idArea, name: $scope.loggedUser.area.name};
                
                    $scope.areas = areasService.getList();
                    if (document != "") {
                        if (document.id == 0) {

                            $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'documentDialog.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: false,
                                    areas: $scope.areas,
                                    fileUpload: fileUpload
                                }
                            });
                        } else {

                            document.fileDate = new Date(document.fileDate);
                            $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'documentDialog.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: true,
                                    areas: $scope.areas,
                                    fileUpload: fileUpload
                                }
                            });
                        }

                    }
                
            }

        }, true);

        function documentDialogController($scope, $http, $timeout, $q, documentosService, document, update, areas, fileUpload, tagsService, documentKeywordRelationshipService)
        {

            setTimeout(function () {

                $('.datepicker').pickadate({
                    selectMonths: true, // Creates a dropdown to control month
                    selectYears: 15 // Creates a dropdown of 15 years to control year
                });


            }, 700);

            $scope.areas = areas;
            $scope.document = document;
            $scope.update = update;
            $scope.tags = [];
            $scope.tagsOriginales = [];
            if ($scope.update == true) {
                $scope.tags = document.keywords;
                $scope.tagsOriginales = $scope.tags;

            }


            var countries = [];

            $scope.getTags = function () {
                tagsService.getTags().then(function (d) {
                    countries = tagsService.getList();
                });
            };

            $scope.getTags();


            $scope.loadCountries = function ($query) {
                return $http.get('/FIMDocumentos/FIMRest/hello/getKeywords', {cache: false}).then(function (response) {
                    countries = response.data;
                    return countries.filter(function (country) {
                        return country.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
                    });
                });
            };

            $scope.viewTags = function () {
                

            }


            $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
            $scope.tagsStuff = function () {
                angular.forEach($scope.tags, function (tag, key) {
                    if ("id" in tag) {
                        $scope.dDto = {
                            idKeyword: tag.id,
                            idDocument: $scope.document.id
                        };
                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                            // console.log(JSON.stringify(data));

                        });

                    } else {
                        tag.createdBy = $("#idUsuario").val();
                        // window.console.log("Tag a crear : " + JSON.stringify(tag));
                        tagsService.createTag(tag).then(function (data) {
                            $scope.dDto = {
                                idKeyword: data.id,
                                idDocument: $scope.document.id
                            };

                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                // console.log(JSON.stringify(data));

                            });
                            countries.push($scope.dDto);
                        });

                    }
                });
            }


            $scope.editDocument = function () {


                documentKeywordRelationshipService.deleteDocumentKeywordRelationshipsByDocument($scope.document).then(function (data) {
                    // window.console.log("Se han borrado las relaciones de este documento");
                });
                $scope.document.keywords = $scope.tags;
                $scope.tagsStuff();
                $scope.document.fileDate = new Date($("#fileDate").val());
                window.console.log($scope.document);
                documentosService.updateDocument($scope.document).then(function (data) {
                    $mdDialog.hide();

                });



            }

            $scope.nuevoDocument = function () {
                
                var file = $scope.myFile;
                console.dir($scope.myFile);
                console.dir($scope.myFile.name);
                $scope.myFile.name = "NuevoNombreDude"
                $scope.document.filename = $scope.myFile.name;
                $scope.document.fileDate = new Date($("#fileDate").val());
                window.console.log(JSON.stringify("Documento : " + JSON.stringify($scope.document)  ));
                var uploadUrl = "/FIMDocumentos/FIMRest/hello/upload";
                var fields = [{"name": "name", "data": document.name}, {"name": "description", "data": document.description}];
                
                var fd = new FormData();
                fd.append('file', file);
                for (var i = 0; i < fields.length; i++) {
                    fd.append(fields[i].name, fields[i].data)
                }
                $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                        .success(function (response) {
                            console.log("Respuesta : " + response);
                            $scope.document.filename = response;
                            documentosService.createDocument($scope.document).then(function (data) {
                                $mdDialog.hide();
                                $scope.document = data;
                                angular.forEach($scope.tags, function (tag, key) {
                                    if ("id" in tag) {
                                        $scope.dDto = {
                                            idKeyword: tag.id,
                                            idDocument: $scope.document.id
                                        };
                                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                        });

                                    } else {
                                        tag.createdBy = $("#idUsuario").val();
                                        
                                        tagsService.createTag(tag).then(function (data) {
                                            $scope.dDto = {
                                                idKeyword: data.id,
                                                idDocument: $scope.document.id
                                            };

                                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                                

                                            });
                                            countries.push($scope.dDto);
                                        });

                                    }
                                });
                                $scope.document.keywords = $scope.tags;
                                documentosService.addToList($scope.document);
                            });





                        })
                        .error(function () {
                            window.console.log("false");
                        });


            };

        }
    }]);




