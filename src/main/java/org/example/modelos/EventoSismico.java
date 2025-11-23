package org.example.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoSismico {

    // Atributos
    private ClasificacionSismo clasificacion;
    private MagnitudRichter magnitud;
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estado;

    //Experto
    private ArrayList<CambioEstado> cambioEstado = new ArrayList<>();

    private List<SerieTemporal> serieTemporal;
    
    private Analista responsable; //Integracion con el analista
    
    
    // Atributos propios
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia;
    private Integer latitudEpicentro;
    private Integer latitudHipocentro;
    private Integer longitudEpicentro;
    private Integer longitudHipocentro;
    private Integer valorMagnitud;

    public EventoSismico() {
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

    // -------------------------------------------------------------
    // MÉTODOS DE NEGOCIO (TRAZABILIDAD CON DIAGRAMA DE SECUENCIA)
    // -------------------------------------------------------------
    /**
     * Responde al mensaje: bloquear() El Gestor solo dice "bloqueate", el
     * evento sabe cómo hacerlo.
     */
    public void bloquear() {
        LocalDateTime ahora = LocalDateTime.now(); 

        // 1. Buscar actual y cerrar (Diagrama: loop -> sosActual -> setFechaHoraFin)
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(ahora);
            }
        }

        // 2. Crear nuevo Estado (Diagrama: new Estado)
        // Usamos un ámbito genérico o hardcodeado según tu diagrama
        Estado estadoBloqueado = new Estado("Revisión", "BloqueadoEnRevision");

        // 3. Crear nuevo CambioEstado (Diagrama: new CambioEstado)
        CambioEstado nuevoCambio = new CambioEstado(estadoBloqueado, ahora);

        // 4. Agregar a la lista
        this.cambioEstado.add(nuevoCambio);

        // 5. Actualizar puntero de estado actual
        this.estado = estadoBloqueado;
    }
    
    
    //Responde al mensaje: rechazar() => Tomar empleado
    public void rechazar(Analista analistaLogueado) {
        LocalDateTime ahora = LocalDateTime.now();

        // 1. Cerrar anterior
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) cambio.setFechaHoraFin(ahora);
        }

        // 2. Crear estado Rechazado
        Estado estadoRech = new Estado("Finalizado", "Rechazado");
        this.cambioEstado.add(new CambioEstado(estadoRech, ahora));
        this.estado = estadoRech;
        
        // 3. Setear fin del evento y responsable
        this.fechaHoraFin = ahora;
        this.setResponsable(analistaLogueado);
    }

    public Analista getResponsable() {
        return responsable;
    }

    public void setResponsable(Analista responsable) {
        this.responsable = responsable;
    }
    
    
    
    
    
    /**
     * Responde al mensaje: rechazar()
     
    public void rechazar() {
        LocalDateTime ahora = LocalDateTime.now();

        // 1. Cerrar anterior
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(ahora);
            }
        }

        // 2. Crear nuevo
        Estado estadoRechazado = new Estado("Finalizado", "Rechazado");
        this.cambioEstado.add(new CambioEstado(estadoRechazado, ahora));
        this.estado = estadoRechazado;

        // 3. Setear fin del evento
        this.fechaHoraFin = ahora;
    }*/

    /**
     * Responde al mensaje: confirmar()
     */
public void confirmar(Analista analistaLogueado) {
        LocalDateTime ahora = LocalDateTime.now();

        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) cambio.setFechaHoraFin(ahora);
        }

        Estado estadoConf = new Estado("Finalizado", "Confirmado");
        this.cambioEstado.add(new CambioEstado(estadoConf, ahora));
        this.estado = estadoConf;
        
        this.fechaHoraFin = ahora;
        this.setResponsable(analistaLogueado);
    }

    public boolean sosAutoDetectado() {
        return this.estado != null && "AutoDetectado".equals(this.estado.getNombreEstado());
    }

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

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }
    
    public void setValorMagnitud(Integer valorMagnitud){
        this.valorMagnitud = valorMagnitud;
    }
    
    public List<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(List<SerieTemporal> serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    public LocalDateTime tomarFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public Integer tomarLatitudEpicentro() {
        return latitudEpicentro;
    }

    public Integer tomarLongitudEpicentro() {
        return longitudEpicentro;
    }

    public Integer getValorMagnitud() {
        return valorMagnitud;
    }

    @Override
    public String toString() {
        return "EventoSismico [estado=" + (estado != null ? estado.getNombreEstado() : "null") + "]";
    }


}
