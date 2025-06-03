package org.example.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoSismico {

    //Atributos relacionales
    private ClasificacionSismo clasificacion; //
    private MagnitudRichter magnitud; //
    private OrigenDeGeneracion origenGeneracion; //
    private AlcanceSismo alcanceSismo; //
    private Estado estado; //
    
    //private Empleado analistaSupervisor;

    private ArrayList<CambioEstado> cambioEstado;
    private List<SerieTemporal> serieTemporal;

    //Atributos propios
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia; //
    private Integer latitudEpicentro; //
    private Integer latitudHipocentro;//
    private Integer longitudEpicentro;//
    private Integer longitudHipocentro;//

    private Integer valorMagnitud;

    public ClasificacionSismo getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionSismo clasificacion) {
        this.clasificacion = clasificacion;
    }

    public MagnitudRichter tomarMagnitud() {
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

    //public Empleado getAnalistaSupervisor() {
    //    return analistaSupervisor;
    //}

    //public void setAnalistaSupervisor(Empleado analistaSupervisor) {
     //   this.analistaSupervisor = analistaSupervisor;
    //}

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public List<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(List<SerieTemporal> serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDateTime tomarFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public Integer tomarLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(Integer latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public Integer tomartLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(Integer latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
    }

    public Integer tomarLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(Integer longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public Integer tomarLongitudHipocentro() {
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
    
    //Metodos creados para trazabilidad 1-1
    public boolean sosAutoDetectado() {
        return this.estado != null && this.estado.getNombreEstado().equals("AutoDetectado");
    }
    
    
    

@Override
public String toString() {
    return "EventoSismico {\n" +
           "  clasificacion: " + clasificacion + ",\n" +
           "  magnitud: " + magnitud + ",\n" +
           "  origenGeneracion: " + origenGeneracion + ",\n" +
           "  alcanceSismo: " + alcanceSismo + ",\n" +
           "  estado: " + estado + ",\n" +
           "  cambioEstado: " + cambioEstado + ",\n" +
           "  serieTemporal: " + serieTemporal + ",\n" +
           "  fechaHoraFin: " + fechaHoraFin + ",\n" +
           "  fechaHoraOcurrencia: " + fechaHoraOcurrencia + ",\n" +
           "  latitudEpicentro: " + latitudEpicentro + ",\n" +
           "  latitudHipocentro: " + latitudHipocentro + ",\n" +
           "  longitudEpicentro: " + longitudEpicentro + ",\n" +
           "  longitudHipocentro: " + longitudHipocentro + ",\n" +
           "  valorMagnitud: " + valorMagnitud + "\n" +
           '}';
}

    public void setResponsableRevision(Analista analista) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
    
    
}
