angular.module('PuntosDeInteresApp')
    .service('ListaPuntosDeInteres',function(){
        this.todosLosPois=function(){
            return  [
                {
                    nombre: 'Cafe Martinez',
                    direccion: 'Av. Alem 950'
                },
                {
                    nombre: 'Libreria Norma',
                    direccion: 'Av. Corrientes 3561'
                },
                {
                    nombre: 'Parada de colectivo - Linea 15',
                    direccion: 'Av. Scalabrini Ortiz 1570'
                },
                {
                    nombre: 'CGP 9',
                    direccion: 'Av Directorio 4360'
                },
                {
                    nombre: 'Tienda de ropa',
                    direccion: 'Av. Santa Fe 2050'
                },
                {
                    nombre: 'CGP 2',
                    direccion: 'J. E. Uriburu 1022'
                },
                {
                    nombre: 'Parada de colectivo - Linea 37',
                    direccion: 'Rodriguez Peña Esq. Corrientes'
                },
                {
                    nombre: 'The design of every day things',
                    direccion: 'Don Norman'
                },
                {
                    nombre: 'The design of every day things',
                    direccion: 'Don Norman'
                },
            ];
        }
    });