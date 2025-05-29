package org.example.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoSismico {

    //Atributos relacionales
    private ClasificacionSismo clasificacion;
    private MagnitudRichter magnitud;
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estado;
    private Empleado analistaSupervisor;

    private ArrayList<CambioEstado> cambioEstado;
    private ArrayList<SerieTemporal> serieTemporal;

    //Atributos propios
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia;
    private Integer latitudEpicentro;
    private Integer latitudHipocentro;
    private Integer longitudEpicentro;
    private Integer longitudHipocentro;

    private Integer valorMagnitud;

    public ClasificacionSismo getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionSismo clasificacion) {
        this.clasificacion = clasificacion;
    }

    public MagnitudRichter getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(MagnitudRichter magnitud) {
        this.magnitud = magnitud;
    }

    public OrigenDeGeneracion getOrigenGeneracion() {
        return origenGeneracion;
    }

    public void setOrigenGeneracion(OrigenDeGeneracion origenGeneracion) {
        this.origenGeneracion = origenGeneracion;
    }

    public AlcanceSismo getAlcanceSismo() {
        return alcanceSismo;
    }

    public void setAlcanceSismo(AlcanceSismo alcanceSismo) {
        this.alcanceSismo = alcanceSismo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getAnalistaSupervisor() {
        return analistaSupervisor;
    }

    public void setAnalistaSupervisor(Empleado analistaSupervisor) {
        this.analistaSupervisor = analistaSupervisor;
    }

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public ArrayList<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(ArrayList<SerieTemporal> serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public Integer getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(Integer latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public Integer getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(Integer latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
    }

    public Integer getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(Integer longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public Integer getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(Integer longitudHipocentro) {
        this.longitudHipocentro = longitudHipocentro;
    }

    public Integer getValorMagnitud() {
        return valorMagnitud;
    }

    public void setValorMagnitud(Integer valorMagnitud) {
        this.valorMagnitud = valorMagnitud;
    }

    public EventoSismico() {
    }
}
