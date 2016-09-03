'use strict';

var app = angular.module('PuntosDeInteresApp',[])

app.controller('controllerBusqueda', function() {
    return new Buscador();
});

function Buscador() {
    this.palabraClave;
    this.listaPalabrasClave = [];

    this.agregarPalabraClave = function(palabraClave) {
        this.listaPalabrasClave.push(palabraClave)
    };

}