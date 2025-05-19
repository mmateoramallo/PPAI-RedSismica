package org.example.modelos;

public class MotivoTipo {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MotivoTipo(String descripcion) {
        this.descripcion = descripcion;
    }

    public MotivoTipo() {
    }

    @Override
    public String toString() {
        return "MotivoTipo{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
