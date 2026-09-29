public class MaquinaDePalomitas extends Maquina {

    private int porcionesPorHora;
    private boolean carritoIntegrado;

    public MaquinaDePalomitas(String codigo, String marca, String modelo, double tarifaDiaria, int porcionesPorHora, boolean carritoIntegrado) {
        super(codigo, marca, modelo, tarifaDiaria);
        this.porcionesPorHora = porcionesPorHora;
        this.carritoIntegrado = carritoIntegrado;
    }

    public int getPorcionesPorHora() {
        return porcionesPorHora;
    }

    public boolean tieneCarritoIntegrado() {
        return carritoIntegrado;
    }

    @Override
    public String getCategoria() {
        return "Maquina de palomitas";
    }

    @Override
    public String mostrarDetalles() {
        String carrito = carritoIntegrado ? "Si" : "No";
        return "Porciones por hora: " + porcionesPorHora + " | Carrito integrado: " + carrito;
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            return 0;
        }

        double total = getTarifaDiaria() * dias;

        if (carritoIntegrado) {
            total += 40 * dias;
        }

        return total;
    }
}