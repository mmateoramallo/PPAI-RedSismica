package org.example.modelos;

public class Rol {

    private String descripcionRol;

    private String nombre;

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol() {
    }


    public Rol(String descripcionRol, String nombre) {
        this.descripcionRol = descripcionRol;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "descripcionRol='" + descripcionRol + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
