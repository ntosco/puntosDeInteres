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
                    function PuntoDeInteres (nombre, point, direccion, tipo, adicionales) {
                        return { nombre: nombre, point: point, direccion: direccion, tipo: tipo, adicionales: adicionales };
                    }

                    return [
                        new PuntoDeInteres("Cafe Martinez", "(-34.703658, -58.393030)", "Gdor. F. Llavallol 80", "Local Comercial", { rubro: "Cafetería"} ),
                        new PuntoDeInteres("Planet Taco | Mexican & American Food", "(-34.702516, -58.393320)", "Gdor. Carlos Tejedor 102", "Local Comercial", { rubro: "Restaurant"} ),
                        new PuntoDeInteres("Banco Itaú", "(-34.703325, -58.391981)", "Av. Hipólito Yrigoyen 4189", "Sucursal de banco", { serviciosAsociados: {"Asistencia Financiera" : "Tramite de alta"} , zona: "Lanus" } ),
                        new PuntoDeInteres("Banco ICBC", "(-34.703915, -58.391966)", "Av. Hipólito Yrigoyen 4227", "Sucursal de banco", { serviciosAsociados: {"Evaluacion para un credito" : "Chequeras"} , zona: "Lanus" } ),
                        new PuntoDeInteres("Linea 45 - Remedios de Escalada", "(-34.704478, -58.391965)", "Avenida Hipólito Yrigoyen 4299", "Parada de colectivo", { numeroDeLinea: "45" } ),
                        new PuntoDeInteres("Linea 33 - Retiro", "(-34.704410, -58.391708)", "Av. Hipólito Yrigoyen 4276", "Parada de colectivo", { numeroDeLinea: "33" } ),
                        new PuntoDeInteres("CGP 9", "(-34.645662, -58.485099)", "Av Directorio 4360", "CGP", { zona: "Comuna 9", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "EcoBici", horario: "10 -14 hs"}]  } ),
                        new PuntoDeInteres("CGP 2", "(-34.596665, -58.398946)", "Pres. José E. Uriburu 1022", "CGP", { zona: "Comuna 2", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "Venta de pasajes - Bus Turístico", horario: "10 -14 hs"}]})
                    ];
                }
            }
        })
});