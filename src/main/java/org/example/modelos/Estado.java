package org.example.modelos;

public class Estado {

    private String ambito;

    private String nombreEstado;

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    public Estado() {
    }

    @Override
    public String toString() {
        return "Estado{" +
                "ambito='" + ambito + '\'' +
                ", nombreEstado='" + nombreEstado + '\'' +
                '}';
    }
}
