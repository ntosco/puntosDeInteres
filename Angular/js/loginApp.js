function Usuario (usuario, contrasena) {
    return { user: usuario, pass: contrasena};
}

var repositorioUsuarios = [
    new Usuario("martin", "samo"),
    new Usuario("nico", "tosco"),
    new Usuario("fer", "haspert"),
    new Usuario("maca", "lepera"),
    new Usuario("sofi", "audisio"),

];

var app = angular.module('LoginApp',[])

app.controller('controllerLogin', function () {
    var self = this
    this.user;
    this.pass;

    this.loginExitoso = function () {
		var found = false;
		for(var i = 0; i < repositorioUsuarios.length; i++) {
			if (repositorioUsuarios[i].user == this.user && repositorioUsuarios[i].pass == this.pass) {
				found = true;
				window.location.href = "index.html";
				break;
			}
		}
		if(found == false){
			alert("nombre de usuario y/o contraseña incorrecto/s");
		}
	};

});