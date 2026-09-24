import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DulceEstacion empresa = new DulceEstacion();

    public static void main(String[] args) {

        cargarDatosIniciales();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

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
                    System.out.println("Gracias por utilizar Dulce Estación.");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcion != 7);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("================================");
        System.out.println("         DULCE ESTACIÓN");
        System.out.println("================================");
        System.out.println("1. Registrar máquina");
        System.out.println("2. Consultar inventario");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Mostrar reporte general");
        System.out.println("7. Salir");
        System.out.println("================================");
    }

    private static void cargarDatosIniciales() {

        // Máquinas de palomitas
        empresa.registrarMaquina(
                new MaquinaDePalomitas(
                        "P001",
                        "Gold Medal",
                        "PopMax",
                        100.00,
                        80,
                        true
                )
        );

        empresa.registrarMaquina(
                new MaquinaDePalomitas(
                        "P002",
                        "FunTime",
                        "Classic",
                        90.00,
                        60,
                        false
                )
        );

        // Máquinas de algodón de azúcar
        empresa.registrarMaquina(
                new MaquinaAlgodon(
                        "A001",
                        "CandyPro",
                        "Turbo",
                        120.00,
                        1200
                )
        );

        empresa.registrarMaquina(
                new MaquinaAlgodon(
                        "A002",
                        "SweetMaker",
                        "Basic",
                        100.00,
                        900
                )
        );

        // Fuentes de chocolate
        empresa.registrarMaquina(
                new FuenteChocolate(
                        "C001",
                        "ChocoFun",
                        "Premium",
                        150.00,
                        2.5
                )
        );

        empresa.registrarMaquina(
                new FuenteChocolate(
                        "C002",
                        "CocoaFlow",
                        "Mini",
                        130.00,
                        1.5
                )
        );
    }

    private static void registrarMaquina() {
        System.out.println();
        System.out.println("--- REGISTRAR MÁQUINA ---");
        System.out.println("1. Máquina de palomitas");
        System.out.println("2. Máquina de algodón de azúcar");
        System.out.println("3. Fuente de chocolate");

        int tipo = leerEntero("Seleccione el tipo de máquina: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de máquina inválido.");
            return;
        }

        String codigo = leerTexto("Código de inventario: ");

        if (empresa.buscarMaquina(codigo) != null) {
            System.out.println("Ya existe una máquina con ese código.");
            return;
        }

        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        double tarifa = leerDecimalPositivo("Tarifa diaria: Q");

        Maquina nuevaMaquina;

        switch (tipo) {
            case 1:
                int porciones = leerEnteroPositivo(
                        "Porciones producidas por hora: "
                );

                boolean carrito = leerSiNo(
                        "¿Tiene carrito integrado? (S/N): "
                );

                nuevaMaquina = new MaquinaDePalomitas(
                        codigo,
                        marca,
                        modelo,
                        tarifa,
                        porciones,
                        carrito
                );
                break;

            case 2:
                int potencia = leerEnteroPositivo(
                        "Potencia en vatios: "
                );

                nuevaMaquina = new MaquinaAlgodon(
                        codigo,
                        marca,
                        modelo,
                        tarifa,
                        potencia
                );
                break;

            case 3:
                double capacidad = leerDecimalPositivo(
                        "Capacidad máxima en kilogramos: "
                );

                nuevaMaquina = new FuenteChocolate(
                        codigo,
                        marca,
                        modelo,
                        tarifa,
                        capacidad
                );
                break;

            default:
                return;
        }

        boolean resultado = empresa.registrarMaquina(nuevaMaquina);

        if (resultado) {
            System.out.println("Máquina registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la máquina.");
        }
    }

    private static void cotizarAlquiler() {
        System.out.println();
        System.out.println("--- COTIZAR ALQUILER ---");

        String codigo = leerTexto("Código de la máquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una máquina con ese código.");
            return;
        }

        int dias = leerEnteroPositivo("Cantidad de días: ");
        double total = maquina.calcularCosto(dias);

        System.out.println();
        System.out.println("---------- COTIZACIÓN ----------");
        System.out.println("Código: " + maquina.getCodigo());
        System.out.println("Categoría: " + maquina.getCategoria());
        System.out.println("Marca: " + maquina.getMarca());
        System.out.println("Modelo: " + maquina.getModelo());

        System.out.printf(
                "Tarifa diaria: Q%.2f%n",
                maquina.getTarifaDiaria()
        );

        System.out.println(
                "Características: " + maquina.mostrarDetalles()
        );

        if (maquina.estaDisponible()) {
            System.out.println("Disponibilidad: Disponible");
        } else {
            System.out.println("Disponibilidad: Alquilada");
        }

        System.out.println("Días solicitados: " + dias);
        System.out.printf("Costo total: Q%.2f%n", total);
        System.out.println("-------------------------------");

        System.out.println(
                "Esta cotización no modifica la disponibilidad ni los ingresos."
        );
    }

    private static void confirmarAlquiler() {
        System.out.println();
        System.out.println("--- CONFIRMAR ALQUILER ---");

        String codigo = leerTexto("Código de la máquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una máquina con ese código.");
            return;
        }

        if (!maquina.estaDisponible()) {
            System.out.println(
                    "La máquina está ocupada y no puede alquilarse."
            );
            return;
        }

        int dias = leerEnteroPositivo("Cantidad de días: ");
        double total = maquina.calcularCosto(dias);

        System.out.println();
        System.out.println("Máquina: " + maquina.getCategoria());
        System.out.println("Código: " + maquina.getCodigo());
        System.out.println("Detalles: " + maquina.mostrarDetalles());
        System.out.println("Días: " + dias);
        System.out.printf("Total que debe pagar: Q%.2f%n", total);

        boolean confirmar = leerSiNo(
                "¿Desea confirmar el alquiler? (S/N): "
        );

        if (!confirmar) {
            System.out.println(
                    "Operación cancelada. No se realizaron cambios."
            );
            return;
        }

        boolean resultado = empresa.confirmarAlquiler(
                codigo,
                dias
        );

        if (resultado) {
            System.out.println("Alquiler confirmado correctamente.");
            System.out.printf("Monto cobrado: Q%.2f%n", total);
        } else {
            System.out.println(
                    "No fue posible completar el alquiler."
            );
        }
    }

    private static void registrarDevolucion() {
        System.out.println();
        System.out.println("--- REGISTRAR DEVOLUCIÓN ---");

        String codigo = leerTexto("Código de la máquina: ");
        Maquina maquina = empresa.buscarMaquina(codigo);

        if (maquina == null) {
            System.out.println("No existe una máquina con ese código.");
            return;
        }

        if (maquina.estaDisponible()) {
            System.out.println(
                    "La máquina ya está disponible. "
                            + "No tiene un alquiler activo."
            );
            return;
        }

        boolean resultado = empresa.registrarDevolucion(codigo);

        if (resultado) {
            System.out.println(
                    "Devolución registrada correctamente."
            );

            System.out.println(
                    "La máquina está disponible nuevamente."
            );
        } else {
            System.out.println(
                    "No fue posible registrar la devolución."
            );
        }
    }

    private static String leerTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(
                        "El dato no puede quedar vacío."
                );
            }

        } while (texto.isEmpty());

        return texto;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();

                return Integer.parseInt(entrada);

            } catch (NumberFormatException error) {
                System.out.println(
                        "Entrada inválida. Debe ingresar un número entero."
                );
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        int numero;

        do {
            numero = leerEntero(mensaje);

            if (numero <= 0) {
                System.out.println(
                        "El número debe ser mayor que cero."
                );
            }

        } while (numero <= 0);

        return numero;
    }

    private static double leerDecimalPositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);

                String entrada = scanner.nextLine()
                        .trim()
                        .replace(',', '.');

                double numero = Double.parseDouble(entrada);

                if (numero > 0) {
                    return numero;
                }

                System.out.println(
                        "El número debe ser mayor que cero."
                );

            } catch (NumberFormatException error) {
                System.out.println(
                        "Entrada inválida. Debe ingresar un número."
                );
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

            System.out.println(
                    "Respuesta inválida. Escriba S o N."
            );
        }
    }
}