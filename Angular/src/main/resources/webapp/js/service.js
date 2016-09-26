app.service("poisService", function($http) {

    this.findAll = function(successCallback) {
        $http.get("/pois").success(successCallback);
    };


    this.logUser = function(successCallback) {
        $http.get("/logUser").success(successCallback);
    };

/*
    this.update = function(poi, successCallback, onFailCallback) {
        $http.put("/pois/" + poi.id, poi).success(successCallback).error(onFailCallback);
    };
*/
});
