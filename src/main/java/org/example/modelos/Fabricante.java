package org.example.modelos;

public class Fabricante {

    private String nombre;
    private String razonSocial;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Fabricante(String nombre, String razonSocial) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
    }

    public Fabricante() {
    }

    @Override
    public String toString() {
        return "Fabricante{" +
                "nombre='" + nombre + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                '}';
    }
}
