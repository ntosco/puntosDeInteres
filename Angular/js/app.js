'use strict';

var app = angular.module('PuntosDeInteresApp',[])

app.controller('controllerBusqueda', function() {
    return new Buscador();
});

function Buscador() {
    var self = this
    this.listaPalabrasClave = [];
    this.palabraClave;

    this.agregarPalabraClave = function() {
        self.listaPalabrasClave.push(self.palabraClave);
        console.log(self.listaPalabrasClave);
    };
}

app.exports = {
    Buscador: Buscador
};