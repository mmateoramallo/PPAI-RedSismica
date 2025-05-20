package org.example.modelos;

public class TipoTareaInspeccion
{
    private Integer codigo;

    private String descripcionTrabajo;

    private String duracionEstimada;

    private String nombre;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(String duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTareaInspeccion(String descripcionTrabajo, Integer codigo, String nombre, String duracionEstimada) {
        this.descripcionTrabajo = descripcionTrabajo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionEstimada = duracionEstimada;
    }


    public TipoTareaInspeccion() {
    }

    @Override
    public String toString() {
        return "TipoTareaInspeccion{" +
                "codigo=" + codigo +
                ", descripcionTrabajo='" + descripcionTrabajo + '\'' +
                ", duracionEstimada='" + duracionEstimada + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
