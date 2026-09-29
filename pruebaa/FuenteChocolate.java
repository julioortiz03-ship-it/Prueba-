public class FuenteChocolate extends Maquina {

    private double capacidadKg;

    public FuenteChocolate(String codigo, String marca, String modelo, double tarifaDiaria, double capacidadKg) {
        super(codigo, marca, modelo, tarifaDiaria);
        this.capacidadKg = capacidadKg;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    @Override
    public String getCategoria() {
        return "Fuente de chocolate";
    }

    @Override
    public String mostrarDetalles() {
        return "Capacidad maxima: " + capacidadKg + " kilogramos";
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            return 0;
        }

        double recargoDiario = capacidadKg * 20;
        return (getTarifaDiaria() + recargoDiario) * dias;
    }
}