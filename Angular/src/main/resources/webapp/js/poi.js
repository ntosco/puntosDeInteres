var Poi = function(){
	var id;
    var nombre = "";
    var direccionNombre = "";
    var ubicacionActual;
    var barrio = "";
    var direccionNumero;
    var listaPalabrasClave;
    var reviews;
 
    var self = this;

    this.favorito = false;

};

Poi.prototype.poseeNombre = function(){
    self.nombre = "nuevoNombre";
};

