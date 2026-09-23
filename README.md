Dulce Estacion necesita un programa en Java para administrar el alquiler de maquinas de palomitas, algodon de azucar y fuentes de chocolate.

Todas las maquinas comparten datos como cadigo, marca, modelo, tarifa y disponibilidad. Sin embargo, cada tipo tiene caracteristicas y formas de calcular el precio diferentes. Por esta razon, el ejercicio busca aplicar principalmente herencia y polimorfismo.

Requisitos del sistema

El programa debe permitir:

Registrar una maquina.
Consultar todas las maquinas del inventario.
Buscar una maquina por su codigo.
Calcular el precio de un alquiler.
Confirmar o cancelar un alquiler.
Registrar la devolucion de una maquina.
Mostrar un reporte de maquinas e ingresos.
Mantener un menu activo hasta seleccionar SALIR.
Informacion de las maquinas

Todas las maquinas deben guardar:

Codigo de inventario.
Marca.
Modelo.
Tarifa diaria.
Disponibilidad.

Ademas, cada categoria guarda informacion particular:

Palomitas: porciones por hora y si posee carrito.
Algodon de azucar: potencia en vatios.
Fuente de chocolate: capacidad maxima en kilogramos.

Reglas de cobro: 
MAQUINA DE PALOMITAS 
-costo normal (tarifa diaria × días)
-Si tiene carrito integrado, se agregan Q40 por cada día ((tarifa diaria + 40) × días)
MAQUINA DE ALGODON 
-costo normal(tarifa diaria × días)
-(tarifa diaria × días) + 60
FUENTE DE CHOCOLATE 
-Se cobran Q20 por cada kilogramo de capacidad y por cada día((tarifa diaria + capacidad × 20) × días)

Clases
private String codigo;
private String marca;
private String modelo;
private double tarifaDiaria;
private boolean disponible;

