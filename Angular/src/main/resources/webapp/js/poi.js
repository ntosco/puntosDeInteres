var Poi = function(){
	var id;
    var nombre = "";
    var direccionNombre = "";
    var ubicacionActual;
    var barrio = "";
    var direccionNumero;
    var listaPalabrasClave;
    var reviews;
 	var tipo = "";
    var linea = "";
    var listaRubros;
    var listaServicios;
    var self = this;
    var valoracionPromedio;

    this.favorito = false;

};

Poi.prototype.poseeNombre = function(){
    self.nombre = "nuevoNombre";
};

