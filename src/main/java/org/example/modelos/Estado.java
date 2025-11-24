package org.example.modelos;

import java.time.LocalDateTime;

public abstract class Estado {

    protected String nombre;

    protected String descripcion;

    public Estado(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Metodos del patron que se van a implementar en los estados concretos, se definen que lacen errores por defdecto
    public void rechazarEvento(EventoSismico evento, LocalDateTime fechaHoraActual, Analista responsable) {
        throw new UnsupportedOperationException("No se puede rechazar el evento en estado " + this.nombre);
    }

    public void confirmarEvento(EventoSismico evento, LocalDateTime fechaHoraActual, Analista responsable) {
        throw new UnsupportedOperationException("No se puede confirmar el evento en estado " + this.nombre);
    }

    // Método bloquear diagrama de la primera entrega
    public void bloquear(EventoSismico evento) {
        throw new UnsupportedOperationException("No se puede bloquear el evento en estado " + this.nombre);
    }

    @Override
    public String toString() {
        return "Estado{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
