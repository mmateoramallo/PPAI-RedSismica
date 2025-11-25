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
    private Estado estado; //Tipo abstracto no se sabe que estado es, pero puede ser cualquiera

    private ArrayList<CambioEstado> cambioEstado = new ArrayList<>();

    //Mapear persistencia
    private Integer idEvento;

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

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

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

    public Analista getResponsable() {
        return responsable;
    }

    public void setResponsable(Analista responsable) {
        this.responsable = responsable;
    }

    public boolean sosAutoDetectado() {
        //return this.estado != null && "AutoDetectado".equals(this.estado.getNombre()); Viejo
        return this.estado instanceof AutoDetectado; //Aplicando el state, aprovechamos para ver si es un objeto de la clase Autodetectado
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

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public void setValorMagnitud(Integer valorMagnitud) {
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

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    //Metodos del STATE - Se delega al estado actual del evento sismico
    public void bloquear() { //Pasos del analisis
        // Delega al estado actual
        this.estado.bloquear(this);
    }

    // Delegación al Estado Actual(Polimorfismo) en ambos confirmar y rechazar
    public void rechazarEvento(LocalDateTime fechaHoraActual, Analista responsableLogueado) {

        this.estado.rechazarEvento(this, fechaHoraActual, responsableLogueado); //Aca se pasa la referencia del evento sismico que se quiere bloquear en el this
        /*To-Do: El estado 'BloqueadoEnRevision' creará el 'new Rechazado()',
        llamará a setEstadoActual(nuevo) y registrará el CambioEstado en la lista histórica, despues sera un estado rechazado*/
    }

    public void confirmarEvento(LocalDateTime fechaHoraActual, Analista responsableLogueado) {
        this.estado.confirmarEvento(this, fechaHoraActual, responsableLogueado);
    }

    /**
     * Diagrama: agregarCambioEstado(nuevoCambio) Se encarga de cerrar el
     * historial anterior y agregar el nuevo.
     *
     * @param nuevoCambio
     *
     * public void agregarCambioEstado(CambioEstado nuevoCambio) { // 1. Cerrar
     * el estado anterior for (CambioEstado c : this.cambioEstado) { if
     * (c.sosActual()) { c.setFechaHoraFin(nuevoCambio.getFechaHoraInicio()); }
     * } // 2. Agregar el nuevo this.cambioEstado.add(nuevoCambio);
    }
     */
    public void agregarCambioEstado(CambioEstado nuevoCambio) {
        this.cambioEstado.add(nuevoCambio);
    }

    /**
     * Diagrama: setEstadoActual(estado) Actualiza el puntero del estado.
     *
     * @param nuevoEstado
     */
    public void setEstadoActual(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "EventoSismico [estado del evento =" + (estado != null ? estado.getNombre() : "null") + "]";
    }

    public CambioEstado getUltimoCambioEstado() {
        if (this.cambioEstado != null && !this.cambioEstado.isEmpty()) {
            // Retorna el último agregado a la lista
            return this.cambioEstado.get(this.cambioEstado.size() - 1);
        }
        return null;
    }

}
