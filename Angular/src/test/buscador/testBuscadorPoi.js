var assert = require("assert");

var Buscador = require(".././app").Buscador;

describe('ComportamientoBuscador', function () {
    describe('Buscador', function() {
        var buscador;

        beforeEach(function() {
            buscador = new Buscador();
        });

        it('debería agregar una palabra clave a la lista de palabras clave', function() {
            buscador.agregarPalabraClave("libreria");
            assert.equal(buscador.listaPalabrasClave.length(), 1);
        });
/*
        it('debería tener deuda 0', function() {
            assert.equal(cliente.deuda, 0);
        });
*/
    });
});