"use strict";

app.controller('controllerLogin', function ($state, poisService) {
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
            if (self.ingresoExitoso)
                $state.go("busquedaPuntosDeInteres");
            else
                alert("nombre de usuario y/o contraseña incorrecto/s");
        });
    };
});

app.controller('controllerBusqueda', function (poisService) {
    var self = this;
    this.listaPalabrasClave = [];
    this.palabraClave = "";
    this.puntosDeInteres = [];
    this.resultadoBusqueda = [];

    this.getPois = function() {
        poisService.findAll(function(data){
            self.puntosDeInteres = _.map(data,function(protoPoi){
                return angular.extend(new Poi(),protoPoi);
            });
            self.resultadoBusqueda = self.puntosDeInteres;
        });
    }

    this.agregarPalabraClave = function() {
        self.listaPalabrasClave.push(self.palabraClave);
        this.palabraClave = "";
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

    this.getPois();

});

app.controller('controllerGeneral',function( $state, $stateParams){

    var idpoi =  _.find(repositorio, function(o) { return o.id == $stateParams.POID; });
    var tipo = idpoi.tipo;

    // Datos relevantes a los tipo de POIS
    this.tipo = tipo;
    this.nombre1 = idpoi.nombre;
    this.direccion = idpoi.direccion;
    this.rubro = idpoi.adicionales.rubro;
    this.servicios = idpoi.adicionales.serviciosAsociados;
    this.zona = idpoi.adicionales.zona;
    this.numeroLinea = idpoi.adicionales.numeroDeLinea;
    this.reviews = idpoi.adicionales.reviews;

    $state.go('detallePOI.' + tipo);

});
