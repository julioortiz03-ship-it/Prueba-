import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static DulceEstacion empresa = new DulceEstacion();

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarMaquina();
                    break;
                case 2:
                    empresa.mostrarInventario();
                    break;
                case 3:
                    cotizarAlquiler();
                    break;
                case 4:
                    confirmarAlquiler();
                    break;
                case 5:
                    registrarDevolucion();
                    break;
                case 6:
                    empresa.mostrarReporte();
                    break;
                case 7:
                    System.out.println("Gracias por utilizar Dulce Estacion.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }

        } while (opcion != 7);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("MENU DULCE ESTACION");
        System.out.println("1. Registrar maquina");
        System.out.println("2. Consultar inventario");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolucion");
        System.out.println("6. Mostrar reporte general");
        System.out.println("7. Salir");
    }

    private static void cargarDatosIniciales() {
        // Maquinas de palomitas
        empresa.registrarMaquina(new MaquinaDePalomitas("P001", "Gold Medal", "PopMax", 100.0, 80, true));
        empresa.registrarMaquina(new MaquinaDePalomitas("P002", "FunTime", "Classic", 90.0, 60, false));

        // Maquinas de algodon
        empresa.registrarMaquina(new MaquinaAlgodon("A001", "CandyPro", "Turbo", 120.0, 1200));
        empresa.registrarMaquina(new MaquinaAlgodon("A002", "SweetMaker", "Basic", 100.0, 900));

        // Fuentes de chocolate
        empresa.registrarMaquina(new FuenteChocolate("C001", "ChocoFun", "Premium", 150.0, 2.5));
        empresa.registrarMaquina(new FuenteChocolate("C002", "CocoaFlow", "Mini", 130.0, 1.5));
    }

    private static void registrarMaquina() {
        System.out.println();
        System.out.println("REGISTRAR MAQUINA");
        System.out.println("1. Maquina de palomitas");
        System.out.println("2. Maquina de algodon de azucar");
        System.out.println("3. Fuente de chocolate");

        int tipo = leerEntero("Seleccione el tipo de maquina: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de maquina invalido.");
            return;
        }

        String codigo = leerTexto("Codigo de inventario: ");

        if (empresa.buscarMaquina(codigo) != null) {
            System.out.println("Ya existe una maquina con ese codigo.");
            return;
        }

        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        double tarifa = leerDecimalPositivo("Tarifa diaria: Q");

        Maquina nuevaMaquina = null;

        switch (tipo) {
            case 1:
                int porciones = leerEnteroPositivo("Porciones producidas por hora: ");
                boolean carrito = leerSiNo("Tiene carrito integrado? (S/N): ");
                nuevaMaquina = new MaquinaDePalomitas(codigo, marca, modelo, tarifa, porciones, carrito);
                break;

            case 2:
                int potencia = leerEnteroPositivo("Potencia en vatios: ");
                nuevaMaquina = new MaquinaAlgodon(codigo, marca, modelo, tarifa, potencia);
                break;

            case 3:
                double capacidad = leerDecimalPositivo("Capacidad maxima en kilogramos: ");
                nuevaMaquina = new FuenteChocolate(codigo, marca, modelo, tarifa, capacidad);
                break;
        }

        if (nuevaMaquina != null && empresa.registrarMaquina(nuevaMaquina)) {
            System.out.println("Maquina registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la maquina.");
        }
    }

    private static void cotizarAlquiler() {
        System.out.println();
        System.out.println("COTIZAR ALQUILER");

        String codigo = leerTexto("Codigo de la maquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una maquina con ese codigo.");
            return;
        }

        int dias = leerEnteroPositivo("Cantidad de dias: ");
        double total = maquina.calcularCosto(dias);

        System.out.println();
        System.out.println("Cotizacion:");
        System.out.println("Codigo: " + maquina.getCodigo());
        System.out.println("Categoria: " + maquina.getCategoria());
        System.out.println("Marca: " + maquina.getMarca());
        System.out.println("Modelo: " + maquina.getModelo());
        System.out.printf("Tarifa diaria: Q%.2f\n", maquina.getTarifaDiaria());
        System.out.println("Detalles: " + maquina.mostrarDetalles());
        System.out.println("Disponibilidad: " + (maquina.estaDisponible() ? "Disponible" : "Alquilada"));
        System.out.println("Dias solicitados: " + dias);
        System.out.printf("Costo total: Q%.2f\n", total);
        System.out.println("Esta cotizacion no modifica la disponibilidad ni los ingresos.");
    }

    private static void confirmarAlquiler() {
        System.out.println();
        System.out.println("CONFIRMAR ALQUILER");

        String codigo = leerTexto("Codigo de la maquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una maquina con ese codigo.");
            return;
        }

        if (!maquina.estaDisponible()) {
            System.out.println("La maquina esta ocupada y no puede alquilarse.");
            return;
        }

        int dias = leerEnteroPositivo("Cantidad de dias: ");
        double total = maquina.calcularCosto(dias);

        System.out.println("Maquina: " + maquina.getCategoria());
        System.out.println("Codigo: " + maquina.getCodigo());
        System.out.println("Detalles: " + maquina.mostrarDetalles());
        System.out.println("Dias: " + dias);
        System.out.printf("Total que debe pagar: Q%.2f\n", total);

        boolean confirmar = leerSiNo("Desea confirmar el alquiler? (S/N): ");

        if (!confirmar) {
            System.out.println("Operacion cancelada.");
            return;
        }

        if (empresa.confirmarAlquiler(codigo, dias)) {
            System.out.println("Alquiler confirmado correctamente.");
            System.out.printf("Monto cobrado: Q%.2f\n", total);
        } else {
            System.out.println("No fue posible completar el alquiler.");
        }
    }

    private static void registrarDevolucion() {
        System.out.println();
        System.out.println("REGISTRAR DEVOLUCION");

        String codigo = leerTexto("Codigo de la maquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una maquina con ese codigo.");
            return;
        }

        if (maquina.estaDisponible()) {
            System.out.println("La maquina ya esta disponible. No tiene un alquiler activo.");
            return;
        }

        if (empresa.registrarDevolucion(codigo)) {
            System.out.println("Devolucion registrada correctamente.");
            System.out.println("La maquina esta disponible nuevamente.");
        } else {
            System.out.println("No fue posible registrar la devolucion.");
        }
    }

    private static String leerTexto(String mensaje) {
        String texto = "";
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El dato no puede quedar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        int num = 0;
        do {
            num = leerEntero(mensaje);
            if (num <= 0) {
                System.out.println("El numero debe ser mayor que cero.");
            }
        } while (num <= 0);
        return num;
    }

    private static double leerDecimalPositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double num = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (num > 0) {
                    return num;
                }
                System.out.println("El numero debe ser mayor que cero.");
            } catch (Exception e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine().trim();
            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }
            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Respuesta invalida. Escriba S o N.");
        }
    }
}