package org.example.modelos;

import java.time.LocalDateTime;

public class CambioEstado {

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Estado estado;

    // Constructor vacío
    public CambioEstado() {}

    /**
     * CORRECCIÓN: Constructor para cumplir con el mensaje "new" del diagrama.
     * Al crearse, nace como "actual" (fin = null).
     */
    public CambioEstado(Estado estado, LocalDateTime fechaHoraInicio) {
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null; 
    }

    /**
     * TRAZABILIDAD: Este método responde al mensaje *sosActual() del diagrama.
     */
    public boolean sosActual() {
        return this.fechaHoraFin == null;
    }

    // Getters y Setters (sin cambios)
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    @Override
    public String toString() {
        return "CambioEstado{" +
                "estado=" + (estado != null ? estado.getNombreEstado() : "null") +
                ", inicio=" + fechaHoraInicio +
                ", fin=" + fechaHoraFin +
                '}';
    }
}