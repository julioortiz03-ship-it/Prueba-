public abstract class Maquina {

    private final String codigo;
    private final String marca;
    private final String modelo;
    private final double tarifaDiaria;
    private boolean disponible;

    protected Maquina(
            String codigo,
            String marca,
            String modelo,
            double tarifaDiaria) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El código no puede estar vacío."
            );
        }

        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La marca no puede estar vacía."
            );
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El modelo no puede estar vacío."
            );
        }

        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException(
                    "La tarifa diaria debe ser mayor que cero."
            );
        }

        this.codigo = codigo.trim();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.tarifaDiaria = tarifaDiaria;

        // Toda máquina nueva comienza disponible.
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public boolean alquilar() {
        if (!disponible) {
            return false;
        }

        disponible = false;
        return true;
    }

    public boolean devolver() {
        if (disponible) {
            return false;
        }

        disponible = true;
        return true;
    }

    protected void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                    "Los días deben ser mayores que cero."
            );
        }
    }

    public abstract String getCategoria();

    public abstract String mostrarDetalles();

    public abstract double calcularCosto(int dias);

    @Override
    public String toString() {
        String estado;

        if (disponible) {
            estado = "Disponible";
        } else {
            estado = "Alquilada";
        }

        return "Código: " + codigo
                + " | Categoría: " + getCategoria()
                + " | Marca: " + marca
                + " | Modelo: " + modelo
                + " | Tarifa diaria: Q"
                + String.format("%.2f", tarifaDiaria)
                + " | " + mostrarDetalles()
                + " | Estado: " + estado;
    }
}