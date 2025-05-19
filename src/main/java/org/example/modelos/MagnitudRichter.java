package org.example.modelos;

public class MagnitudRichter {

    private String descripcionMagnitud;

    private Integer numero;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescripcionMagnitud() {
        return descripcionMagnitud;
    }

    public void setDescripcionMagnitud(String descripcionMagnitud) {
        this.descripcionMagnitud = descripcionMagnitud;
    }

    public MagnitudRichter(String descripcionMagnitud, Integer numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public MagnitudRichter() {
    }

    @Override
    public String toString() {
        return "MagnitudRichter{" +
                "descripcionMagnitud='" + descripcionMagnitud + '\'' +
                ", numero=" + numero +
                '}';
    }
}
