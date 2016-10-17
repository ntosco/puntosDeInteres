"use strict";

app.service("poisService", function($http) {


    this.findAll = function(successCallback) {
        $http.get("/pois").success(successCallback);
    };


    this.logUser = function(usuario, successCallback) {
        $http.get("/logUser/" + usuario.nombre + "/" + usuario.pass).success(successCallback);
    };

     this.favoritosSearch = function(nomUsuario, successCallback) {
        $http.get("/favoritos/" + nomUsuario).success(successCallback);
    };

    this.searchPoi = function(listaPalabrasClave, successCallback){
        $http.get("/searchPoi/" + listaPalabrasClave).success(successCallback);
    };  

    this.buscarUnPoi = function(idPoi, successCallback) {
        $http.get("/buscarUnPoi/" + idPoi).success(successCallback);
    };

    this.updateComentario = function(comentario, valoracion, idPoi, user, successCallback){
        var unaReview = {   comentario: comentario,
                            valoracion: valoracion,
                            user: user};
                            
        $http.put("/comentario/" + idPoi, unaReview).success(successCallback);
    };

    this.update = function(id,fav,user, successCallback) {
        var usuarioFavorito = {   favorito: fav,
                                  usuario: user};

        $http.put("/pois/" + id ,usuarioFavorito).success(successCallback);
    };


/*
    this.update = function(poi, successCallback, onFailCallback) {
        $http.put("/pois/" + poi.id, poi).success(successCallback).error(onFailCallback);
    };
*/
});
