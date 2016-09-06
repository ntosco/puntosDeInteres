app.controller('controllerLogin', function ($state, repositorioUsuarios) {
    var self = this;
    this.user;
    this.pass;

    this.loginExitoso = function () {
        var found = false;
        for(var i = 0; i < repositorioUsuarios.length; i++) {
            if (repositorioUsuarios[i].user == this.user && repositorioUsuarios[i].pass == this.pass) {
                found = true;
                $state.go("busquedaPuntosDeInteres");
                break;
            }
        }
        if(found == false){
            alert("nombre de usuario y/o contraseña incorrecto/s");
        }
    };
});

app.controller('controllerBusqueda', function (repositorio) {
    var self = this;
    this.listaPalabrasClave = [];
    this.palabraClave = "";
    this.resultadoBusqueda = repositorio;

    this.agregarPalabraClave = function() {
        self.listaPalabrasClave.push(self.palabraClave);
        this.palabraClave = "";
    };

    this.buscarPuntoDeInteres = function() {
        var condicionDeBusqueda = function (value) {
            return ( value.nombre.includes(self.listaPalabrasClave) || value.direccion.includes(self.listaPalabrasClave) )
        };
        this.resultadoBusqueda = repositorio.filter(condicionDeBusqueda);
    };

    this.limpiarBusqueda = function(){
        self.listaPalabrasClave = [];
        self.resultadoBusqueda = repositorio;
    }

});

app.controller('controllerGeneral',function( $state, $stateParams,repositorio){

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

    $state.go('detallePOI.' + tipo);

});
