public class MaquinaAlgodon extends Maquina {

    private int potenciaVatios;

    public MaquinaAlgodon(String codigo, String marca, String modelo, double tarifaDiaria, int potenciaVatios) {
        super(codigo, marca, modelo, tarifaDiaria);
        this.potenciaVatios = potenciaVatios;
    }

    public int getPotenciaVatios() {
        return potenciaVatios;
    }

    @Override
    public String getCategoria() {
        return "Maquina de algodon de azucar";
    }

    @Override
    public String mostrarDetalles() {
        return "Potencia: " + potenciaVatios + " vatios";
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            return 0;
        }

        double total = getTarifaDiaria() * dias;

        if (potenciaVatios > 1000) {
            total += 60;
        }

        return total;
    }
}