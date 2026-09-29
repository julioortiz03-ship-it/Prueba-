import java.util.ArrayList;

public class DulceEstacion {

    private ArrayList<Maquina> inventario;
    private double ingresosAcumulados;

    public DulceEstacion() {
        this.inventario = new ArrayList<>();
        this.ingresosAcumulados = 0.0;
    }

    public boolean registrarMaquina(Maquina maquina) {
        if (maquina == null) {
            return false;
        }

        if (buscarMaquina(maquina.getCodigo()) != null) {
            return false;
        }

        inventario.add(maquina);
        return true;
    }

    public Maquina buscarMaquina(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Maquina maquina : inventario) {
            if (maquina.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return maquina;
            }
        }

        return null;
    }

    public double cotizar(String codigo, int dias) {
        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null || dias <= 0) {
            return -1.0;
        }
        return maquina.calcularCosto(dias);
    }

    public boolean confirmarAlquiler(String codigo, int dias) {
        if (dias <= 0) {
            return false;
        }

        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null || !maquina.estaDisponible()) {
            return false;
        }

        double costo = maquina.calcularCosto(dias);
        boolean alquilado = maquina.alquilar();

        if (alquilado) {
            this.ingresosAcumulados += costo;
            return true;
        }

        return false;
    }

    public boolean registrarDevolucion(String codigo) {
        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null) {
            return false;
        }
        return maquina.devolver();
    }

    public void mostrarInventario() {
        System.out.println();
        System.out.println("INVENTARIO DE MAQUINAS");

        if (inventario.isEmpty()) {
            System.out.println("No hay maquinas registradas en el inventario.");
        } else {
            for (Maquina maquina : inventario) {
                System.out.println(maquina);
            }
        }
    }

    public void mostrarReporte() {
        System.out.println();
        System.out.println("REPORTE GENERAL");

        int totalMaquinas = inventario.size();
        int disponibles = 0;
        int alquiladas = 0;

        for (Maquina maquina : inventario) {
            if (maquina.estaDisponible()) {
                disponibles++;
            } else {
                alquiladas++;
            }
        }

        System.out.println("Total de maquinas: " + totalMaquinas);
        System.out.println("Maquinas disponibles: " + disponibles);
        System.out.println("Maquinas alquiladas: " + alquiladas);
        System.out.printf("Total de ingresos acumulados: Q%.2f\n", ingresosAcumulados);
    }

    public ArrayList<Maquina> getInventario() {
        return inventario;
    }

    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }
}
