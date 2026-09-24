public class FuenteChocolate extends Maquina {

    private final double capacidadKg;

    public FuenteChocolate(
            String codigo,
            String marca,
            String modelo,
            double tarifaDiaria,
            double capacidadKg) {

        super(codigo, marca, modelo, tarifaDiaria);

        if (capacidadKg <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero."
            );
        }

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
        return "Capacidad máxima: "
                + capacidadKg
                + " kilogramos";
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

      
        double recargoDiario = capacidadKg * 20;

        return (getTarifaDiaria() + recargoDiario) * dias;
    }
}