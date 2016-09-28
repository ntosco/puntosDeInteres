"use strict";

app.controller('controllerLogin', function ($state,$rootScope, poisService) {
    var self = this;
    this.user;
    this.pass;
    this.ingresoExitoso = false;

    this.setIngreso = function (respuesta){
        self.ingresoExitoso = respuesta;
        self.ingresoExitoso = respuesta;
    }

    this.loguearUsuario = function () {
        poisService.logUser(new Usuario(self.user,self.pass), function (response){
            self.setIngreso(response);
            if (self.ingresoExitoso){
                $rootScope.User = new Usuario(self.user,self.pass);
                $state.go("busquedaPuntosDeInteres");
            }
            else
                alert("nombre de usuario y/o contraseña incorrecto/s");
        });
    };
});

app.controller('controllerBusqueda', function ($rootScope,poisService) {
    var self = this;
    this.listaPalabrasClave = [];
    this.palabraClave = "";
    this.puntosDeInteres = [];
    this.resultadoBusqueda = [];
    this.favoritos = [];

    this.getPois = function() {
        poisService.findAll(function(data){
            self.puntosDeInteres = _.map(data,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });
            self.resultadoBusqueda = self.puntosDeInteres;
            self.getFavoritos();
        });
    };

    this.getFavoritos = function() {
        poisService.favoritosSearch( $rootScope.User.nombre, function(data){
            self.favoritos = _.map(data,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });

            self.actualizarFavoritos();

        });
    };

    this.agregarPalabraClave = function() {
        self.listaPalabrasClave.push(self.palabraClave);
        this.palabraClave = "";
    };

    this.modificarNombres = function(){
        var nombres = _.map(self.favoritos,function(fav){
                return fav.nombre;
        });

        return nombres;
    };

    this.actualizarFavoritos = function(){
        var auxiliar = self.modificarNombres();

        self.puntosDeInteres =  _.map(self.resultadoBusqueda,function(poi2){

            if (poi2.nombre.includes(auxiliar)){
                poi2.favorito = true;
            };       
            
            return poi2;
        });

        self.resultadoBusqueda = self.puntosDeInteres;
    };

    this.buscarPuntoDeInteres = function() {
        poisService.searchPoi(self.listaPalabrasClave, function (data) {
            self.resultadoBusqueda = _.map(data,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });
        });
    };

    this.limpiarBusqueda = function(){
        self.listaPalabrasClave = [];
        self.resultadoBusqueda = self.puntosDeInteres;
    }

    /* Inicializacion */

    this.getPois();

});

app.controller('controllerGeneral',function( $state, $stateParams, poisService){

    //var idpoi =  _.find(repositorio, function(o) { return o.id == $stateParams.POID; });
   // var tipo = idpoi.tipo;

    var self = this;

    this.poiEncontrado = [];
 
    // Datos relevantes a los tipo de POIS
    // 
    this.id = $stateParams.POID;

    this.enviarComentario = function(){
        /* Agregar */
    };

    this.buscarUnPoi = function() {
        poisService.buscarUnPoi(self.id, function(data){
            self.poiEncontrado = angular.extend(new Poi(),data);
        });
    };

    /* Inicializacion */

    this.buscarUnPoi();   
    
/*  this.tipo = tipo;
    this.nombre1 = idpoi.nombre;
    this.direccion = idpoi.direccion;
    this.rubro = idpoi.adicionales.rubro;
    this.servicios = idpoi.adicionales.serviciosAsociados;
    this.zona = idpoi.adicionales.zona;
    this.numeroLinea = idpoi.adicionales.numeroDeLinea;
    this.reviews = idpoi.adicionales.reviews; */

 //   $state.go('detallePOI.' + tipo);


/*
        var self = this;
    this.poi;
    this.id = $stateParams.POID;
    this.comentario = "";
    this.valoracion = "";
       
        poisService.buscarUnPoi(this.id, function (response){
            self.nombre = response.nombre;

            self.poi = _.map(response,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });

            this.nombre = self.poi.nombre;

        });


    this.enviarComentario = function(){

    };

*/
});
