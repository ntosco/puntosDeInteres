app.config(function ($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('logueoState',{
            url:"/",
            templateUrl: "partials/login.html",
            controller: "controllerLogin as loginCtrl"
        })
        .state('busquedaPuntosDeInteres',{
            url:"/busqueda",
            templateUrl: "partials/busquedaPuntosDeinteres.html",
            controller: "controllerBusqueda as busquedaCtrl"
        })
       .state('detallePOI', {
             url: "/detalleGeneral",
             params: { POID: 0 , fav: false, user: ""},
             templateUrl: "detallePOI.html",
             controller: "controllerGeneral as crtlGeneral"
        })
        .state('detallePOI.CGP',{
            url: "/CGP",
            templateUrl: "partials/detallePOI.CGP.html"
        })
        .state('detallePOI.sucursalBanco',{
            url: "/sucursalBanco",
            templateUrl: "partials/detallePOI.sucursalBanco.html"
        })
        .state('detallePOI.lineaColectivo',{
            url: "/lineaColectivo",
            templateUrl: "partials/detallePOI.lineaColectivo.html"
        })
        .state('detallePOI.localComercial',{
            url: "/localComercial",
            templateUrl: "partials/detallePOI.localComercial.html"
        })
});