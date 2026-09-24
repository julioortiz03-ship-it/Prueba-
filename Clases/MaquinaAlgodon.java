public class MaquinaAlgodon extends Maquina {

    private final int potenciaVatios;

    public MaquinaAlgodon(
            String codigo,
            String marca,
            String modelo,
            double tarifaDiaria,
            int potenciaVatios) {

        super(codigo, marca, modelo, tarifaDiaria);

        if (potenciaVatios <= 0) {
            throw new IllegalArgumentException(
                    "La potencia debe ser mayor que cero."
            );
        }

        this.potenciaVatios = potenciaVatios;
    }

    public int getPotenciaVatios() {
        return potenciaVatios;
    }

    @Override
    public String getCategoria() {
        return "Máquina de algodón de azúcar";
    }

    @Override
    public String mostrarDetalles() {
        return "Potencia: " + potenciaVatios + " vatios";
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double total = getTarifaDiaria() * dias;

        if (potenciaVatios > 1000) {
            total += 60;
        }

        return total;
    }
}