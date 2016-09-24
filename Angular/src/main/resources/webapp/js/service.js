app.service("PoisService", function($http) {

    this.findAll = function(successCallback) {
        $http.get("/pois").success(successCallback);
    };

/*
    this.update = function(poi, successCallback, onFailCallback) {
        $http.put("/pois/" + poi.id, poi).success(successCallback).error(onFailCallback);
    };
*/
});
