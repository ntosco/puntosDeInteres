app.service("poisService", function($http) {

    this.findAll = function(successCallback) {
        $http.get("/pois").success(successCallback);
    };


    this.logUser = function(usuario, successCallback) {
        $http.get("/logUser/" + usuario.nombre + "/" + usuario.pass).success(successCallback);
    };

/*
    this.update = function(poi, successCallback, onFailCallback) {
        $http.put("/pois/" + poi.id, poi).success(successCallback).error(onFailCallback);
    };
*/
});
