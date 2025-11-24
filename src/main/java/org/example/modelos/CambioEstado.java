package org.example.modelos;

import java.time.LocalDateTime;

public class CambioEstado {

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Estado estado;
    private Analista responsable; //Para el historial
    
    // Constructor vacío
    public CambioEstado() {}
    
    
    
    //Constructor para el state
    public CambioEstado(Estado estado, LocalDateTime fechaHoraInicio, Analista responsable) {
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.responsable = responsable;
        this.fechaHoraFin = null; // Es el actual
    }
    
    /**
     * CORRECCIÓN: Constructor para cumplir con el mensaje "new" del diagrama.
     * Al crearse, nace como "actual" (fin = null).
     * @param estado
     * @param fechaHoraInicio
     */
    public CambioEstado(Estado estado, LocalDateTime fechaHoraInicio) {
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null; 
    }

    /**
     * Este método responde al mensaje *sosActual() del diagrama.
     * @return 
     */
    public boolean sosActual() {
        return this.fechaHoraFin == null;
    }

    // Getters y Setters
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    public Analista getResponsable() {
        return responsable;
    }

    public void setResponsable(Analista responsable) {
        this.responsable = responsable;
    }
    
    
    @Override
    public String toString() {
        return "CambioEstado{" +
                "estado=" + (estado != null ? estado.getNombre(): "null") +
                ", inicio=" + fechaHoraInicio +
                ", fin=" + fechaHoraFin +
                '}';
    }
}