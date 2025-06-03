package org.example.modelos;

import java.time.LocalDateTime;

public class CambioEstado {

    //Atributos propios
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private Estado estado;


    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public CambioEstado() {
    }

    @Override
    public String toString() {
        return "CambioEstado{" +
               "fechaHoraInicio=" + fechaHoraInicio +
               ", fechaHoraFin=" + fechaHoraFin +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "null") +
               '}';
    }
}
