public class MaquinaDePalomitas extends Maquina {

    private final int porcionesPorHora;
    private final boolean carritoIntegrado;

    public MaquinaDePalomitas(
            String codigo,
            String marca,
            String modelo,
            double tarifaDiaria,
            int porcionesPorHora,
            boolean carritoIntegrado) {

        super(codigo, marca, modelo, tarifaDiaria);

        if (porcionesPorHora <= 0) {
            throw new IllegalArgumentException(
                    "Las porciones por hora deben ser mayores que cero."
            );
        }

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
        return "Máquina de palomitas";
    }

    @Override
    public String mostrarDetalles() {
        String carrito;

        if (carritoIntegrado) {
            carrito = "Sí";
        } else {
            carrito = "No";
        }

        return "Porciones por hora: " + porcionesPorHora
                + " | Carrito integrado: " + carrito;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double total = getTarifaDiaria() * dias;

        
        if (carritoIntegrado) {
            total += 40 * dias;
        }

        return total;
    }
}