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
        $http.put("/comentario/" + idPoi + "/" + comentario + "/" + valoracion + "/" + user).success(successCallback);
    };


/*
    this.update = function(poi, successCallback, onFailCallback) {
        $http.put("/pois/" + poi.id, poi).success(successCallback).error(onFailCallback);
    };
*/
});
