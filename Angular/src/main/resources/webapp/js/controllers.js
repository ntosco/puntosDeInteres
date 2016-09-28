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
    this.STRINGlistaPalabrasClave = "";
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
        self.STRINGlistaPalabrasClave += "!" + self.palabraClave;
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
        poisService.searchPoi(self.STRINGlistaPalabrasClave, function (data) {
            self.resultadoBusqueda = _.map(data,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });
        });
    };

    this.limpiarBusqueda = function(){
        self.listaPalabrasClave = [];
        self.STRINGlistaPalabrasClave = "";
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
    this.id = $stateParams.POID;
    this.fav = $stateParams.fav;
    this.comentario = "";
    this.valoracion;
    var tipo = self.poiEncontrado.tipo;

    this.enviarComentario = function(){
        /* Agregar */
    };

    this.buscarUnPoi = function() {
        poisService.buscarUnPoi(self.id, function(data){
            self.poiEncontrado = angular.extend(new Poi(),data);
            $state.go('detallePOI.' + self.poiEncontrado.tipo);
        });
    };

    /* Inicializacion */

    this.buscarUnPoi();   

});
