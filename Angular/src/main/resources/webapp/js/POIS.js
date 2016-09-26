var Poiss = function(){

};

function LocalComercial() {

	// Agregar que "herede" de POI los valores que se repiten.

    this.nombre = 0;
    this.direccion = 0;
    this.listaRubros = [];
    
}

function SucursalCGP() {

	// Agregar que "herede" de POI los valores que se repiten.

    this.nombre = 0;
    this.direccion = 0;
    this.zona = 0;
    this.listaServicios = [];

    //Agregar Jornada para el horario de atencion
    
}

function LineaColectivo() {

	// Agregar que "herede" de POI los valores que se repiten.

    this.nombreLinea = 0; 	// Es lo mismo que el nombre de un POI
    this.direccion = 0;

    
}