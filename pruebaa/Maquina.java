public abstract class Maquina {

    private String codigo;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponible;

    public Maquina(String codigo, String marca, String modelo, double tarifaDiaria) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
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

    public abstract String getCategoria();

    public abstract String mostrarDetalles();

    public abstract double calcularCosto(int dias);

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Alquilada";
        return "Codigo: " + codigo
                + " | Categoria: " + getCategoria()
                + " | Marca: " + marca
                + " | Modelo: " + modelo
                + " | Tarifa diaria: Q" + tarifaDiaria
                + " | " + mostrarDetalles()
                + " | Estado: " + estado;
    }
}