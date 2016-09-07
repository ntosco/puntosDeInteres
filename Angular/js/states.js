app.config(function ($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('logueoState',{
            url:"/",
            templateUrl: "partials/login.html",
            controller: "controllerLogin as loginCtrl",
            resolve: {
                repositorioUsuarios: function(){
                    function Usuario (usuario, contrasena) {
                        return { user: usuario, pass: contrasena};
                    }

                    return [
                        new Usuario("martin", "samo"),
                        new Usuario("nico", "tosco"),
                        new Usuario("fer", "haspert"),
                        new Usuario("maca", "lepera"),
                        new Usuario("sofi", "audisio")

                    ];
                }
            }
        })
        .state('busquedaPuntosDeInteres',{
            url:"/busqueda",
            templateUrl: "partials/busquedaPuntosDeinteres.html",
            controller: "controllerBusqueda as busquedaCtrl",
            resolve: {
                repositorio: function(){
                    function PuntoDeInteres (identificador, nombre, point, direccion, tipo, adicionales) {
                        return { id: identificador, nombre: nombre, point: point, direccion: direccion, tipo: tipo, adicionales: adicionales };
                    }

                    return [
                        new PuntoDeInteres("1", "Cafe Martinez", "(-34.703658, -58.393030)", "Gdor. F. Llavallol 80", "Local Comercial", { rubro: "Cafetería"} ),
                        new PuntoDeInteres("2","Planet Taco | Mexican & American Food", "(-34.702516, -58.393320)", "Gdor. Carlos Tejedor 102", "Local Comercial", { rubro: "Restaurant"} ),
                        new PuntoDeInteres("3","Banco Itaú", "(-34.703325, -58.391981)", "Av. Hipólito Yrigoyen 4189", "Sucursal de banco", { serviciosAsociados: ["Asistencia Financiera" , "Tramite de alta", "Pago de Tarjeta"] , zona: "Lanus" } ),
                        new PuntoDeInteres("4","Banco ICBC", "(-34.703915, -58.391966)", "Av. Hipólito Yrigoyen 4227", "Sucursal de banco", { serviciosAsociados: ["Evaluacion para un credito" , "Chequeras", "Asesoramiento"] , zona: "Boedo" } ),
                        new PuntoDeInteres("5","Linea 45 - Remedios de Escalada", "(-34.704478, -58.391965)", "Avenida Hipólito Yrigoyen 4299", "Parada de colectivo", { numeroDeLinea: "45" } ),
                        new PuntoDeInteres("6","Linea 33 - Retiro", "(-34.704410, -58.391708)", "Av. Hipólito Yrigoyen 4276", "Parada de colectivo", { numeroDeLinea: "33" } ),
                        new PuntoDeInteres("7","CGP 9", "(-34.645662, -58.485099)", "Av Directorio 4360", "CGP", { zona: "Comuna 9", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "EcoBici", horario: "10 -14 hs"}]  } ),
                        new PuntoDeInteres("8","CGP 2", "(-34.596665, -58.398946)", "Pres. José E. Uriburu 1022", "CGP", { zona: "Comuna 2", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "Venta de pasajes - Bus Turístico", horario: "10 -14 hs"}]})
                    ];
                }
            }
        })
       .state('detallePOI', {
             url: "/detalleGeneral/:POID",
             templateUrl: "ejemplo.html",
             controller: "controllerGeneral as crtlGeneral",
              resolve: {
                repositorio: function(){
                    function PuntoDeInteres (identificador, nombre, point, direccion, tipo, adicionales) {
                        return { id: identificador, nombre: nombre, point: point, direccion: direccion, tipo: tipo, adicionales: adicionales };
                    }

                    return [
                        new PuntoDeInteres("1", "Cafe Martinez", "(-34.703658, -58.393030)", "Gdor. F. Llavallol 80", "localComercial", { rubro: "Cafetería"} ),
                        new PuntoDeInteres("2","Planet Taco | Mexican & American Food", "(-34.702516, -58.393320)", "Gdor. Carlos Tejedor 102", "localComercial", { rubro: "Restaurant"} ),
                        new PuntoDeInteres("3","Banco Itaú", "(-34.703325, -58.391981)", "Av. Hipólito Yrigoyen 4189", "sucursalBanco", { serviciosAsociados: ["Asistencia Financiera" , "Tramite de alta", "Pago de Tarjeta"] , zona: "Lanus" } ),
                        new PuntoDeInteres("4","Banco ICBC", "(-34.703915, -58.391966)", "Av. Hipólito Yrigoyen 4227", "sucursalBanco", { serviciosAsociados: ["Evaluacion para un credito" , "Chequeras", "Asesoramiento"] , zona: "Boedo" } ),
                        new PuntoDeInteres("5","Linea 45 - Remedios de Escalada", "(-34.704478, -58.391965)", "Avenida Hipólito Yrigoyen 4299", "lineaColectivo", { numeroDeLinea: "45" } ),
                        new PuntoDeInteres("6","Linea 33 - Retiro", "(-34.704410, -58.391708)", "Av. Hipólito Yrigoyen 4276", "lineaColectivo", { numeroDeLinea: "33" } ),
                        new PuntoDeInteres("7","CGP 9", "(-34.645662, -58.485099)", "Av Directorio 4360", "CGP", { zona: "Comuna 9", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "EcoBici", horario: "10 -14 hs"}]  } ),
                        new PuntoDeInteres("8","CGP 2", "(-34.596665, -58.398946)", "Pres. José E. Uriburu 1022", "CGP", { zona: "Comuna 2", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "Venta de pasajes - Bus Turístico", horario: "10 -14 hs"}]})
                    ];
                }
            }
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