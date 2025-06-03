package org.example.gestor;

import dbCoded.EventoGenerator;
import dbCoded.ObjectFactory;
import org.example.modelos.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorRegistrarResultadoDeRevisionManual {

    private final EventoGenerator generador    = new EventoGenerator();
    private final ObjectFactory  baseHardcodeada = new ObjectFactory();

    /**
     * 1) Lista fija de todos los eventos “completos” generados al iniciar.
     *    Cada objeto ya contiene:
     *      - magnitud (MagnitudRichter)
     *      - alcance (AlcanceSismo)
     *      - clasificación (ClasificacionSismo)
     *      - origen (OrigenDeGeneracion)
     *      - series temporales (SerieTemporal → MuestraSismica → DetalleMuestraSismica)
     *      - estado inicial (uno de los valores de ObjectFactory.generarEstados(), por ejemplo “AutoDetectado” para algunos).
     */
    private final List<EventoSismico> eventos = generador.generarEventosCompletos();

    /** 2) Cada vez que llamamos a nuevaRevision(), se refresca esta lista con SOLO los autos detectados. */
    private List<EventoSismico> eventosNoDet;

    // ------------------------------------------
    // PASO 5/6: Iniciar la revisión de eventos
    // ------------------------------------------

    /** 
     * Paso 5/6: “Iniciando Revisión de eventos sísmicos”.
     * Filtra los eventos de estado “AutoDetectado” que aún no fueron revisados.
     */
    public void nuevaRevision() {
        System.out.println("Iniciando Revisión de eventos sísmicos");
        eventosNoDet = buscarEventoNoDet();
    }

    /**
     * Paso 6: Filtrar SOLO aquellos eventos cuyo estado sea “AutoDetectado”.
     *   Devuelve referencias directas a los objetos originales, ordenadas por fecha/hora ocurrencia (descendente).
     */
    public List<EventoSismico> buscarEventoNoDet() {
        return eventos.stream()
                .filter(EventoSismico::sosAutoDetectado)
                .sorted((e1, e2) ->
                    e2.tomarFechaHoraOcurrencia().compareTo(e1.tomarFechaHoraOcurrencia()))
                .collect(Collectors.toList());
    }

    // ------------------------------------------
    // PASO 8 y 9: Bloquear el evento para revisión
    // ------------------------------------------

    /**
     * Paso 8: “bloquearEventoSismico(evento)”.
     *  1) Cambia el estado a “BloqueadoEnRevision”.  
     *  2) Registra un nuevo CambioEstado con la fecha de inicio de bloqueo.  
     *  3) Asegura que las series temporales estén cargadas (aunque el original ya las traía).
     */
    public void bloquearEventoSismico(EventoSismico evento) {
        // 1) Cambiar estado a “BloqueadoEnRevision”
        cambiarEstadoEvento(evento, "BloqueadoEnRevision");

        // 2) Registrar el objeto CambioEstado
        LocalDateTime ahora = getFechaHoraActual();
        CambioEstado cambio = new CambioEstado();
        cambio.setEstado(evento.getEstado());        // Estado = BloqueadoEnRevision
        cambio.setFechaHoraInicio(ahora);            // Fecha/hora en que se bloqueó

        if (evento.getCambioEstado() == null) {
            evento.setCambioEstado(new ArrayList<>());
        }
        evento.getCambioEstado().add(cambio);

        // 3) Asegurarnos de que las series temporales estén cargadas.
        //    (Los objetos originales ya venían con sus series de EventoGenerator,
        //     pero aquí las regeneramos “simuladas” para el detalle.)
        evento.setSerieTemporal(baseHardcodeada.generarSeriesTemporalesParaEvento());
    }

    /**
     * Paso 8.2: “getFechaHoraActual()” devuelve la hora actual.
     */
    public LocalDateTime getFechaHoraActual() {
        return LocalDateTime.now();
    }

    /**
     * Paso 8.3: “buscarBloqEnRevision(evento)” devuelve el último CambioEstado.
     *  Es útil si queremos (por ejemplo) conocer el instante exacto en que se bloqueó.
     */
    public CambioEstado buscarBloqEnRevision(EventoSismico evento) {
        List<CambioEstado> cambios = evento.getCambioEstado();
        if (cambios == null || cambios.isEmpty()) {
            return null;
        }
        // Retornamos el último cambio agregado
        return cambios.get(cambios.size() - 1);
    }

    /**
     * Paso 8.3: “sosBloqPorRev(evento)” → true si su estado actual es “BloqueadoEnRevision”.
     */
    public boolean sosBloqPorRev(EventoSismico evento) {
        return evento.getEstado() != null &&
               evento.getEstado().getNombreEstado().equals("BloqueadoEnRevision");
    }

    // ------------------------------------------
    // PASO 9.1 y 9.2: Obtener datos sísmicos completos
    // ------------------------------------------

    /**
     * Paso 9.1: “tomarAlcance(evento)”. Retorna el objeto completo AlcanceSismo asociado.
     */
    public AlcanceSismo tomarAlcance(EventoSismico evento) {
        return evento.getAlcanceSismo();
    }

    /**
     * Paso 9.1: “tomarClasificacion(evento)”. Retorna el objeto completo ClasificacionSismo.
     */
    public ClasificacionSismo tomarClasificacion(EventoSismico evento) {
        return evento.getClasificacion();
    }

    /**
     * Paso 9.1: “tomarOrigen(evento)”. Retorna el objeto completo OrigenDeGeneracion.
     */
    public OrigenDeGeneracion tomarOrigen(EventoSismico evento) {
        return evento.getOrigenGeneracion();
    }

    /**
     * Paso 9.2: “tomarSeriesTemporales(evento)”. Devuelve la lista completa de SerieTemporal.
     */
    public List<SerieTemporal> tomarSeriesTemporales(EventoSismico evento) {
        return evento.getSerieTemporal();
    }

    // ------------------------------------------
    // PASO 7: Interacción con la UI (selección)
    // ------------------------------------------

    /**
     * Paso 7: “pedirSeleccionEvento()”. Se invoca si el usuario no ha seleccionado ningún registro
     * en la tabla después de X segundos (o hizo doble‐clic fuera de filas). Solo deja un registro en consola.
     */
    public void pedirSeleccionEvento() {
        System.out.println("Gestor: por favor seleccione un evento de la tabla.");
    }

    /**
     * Paso 7: “tomarEventoSeleccionado(evt)”. Se invoca cuando la UI ya sabe qué fila el usuario eligió.
     * Aquí podríamos guardarlo en un log o auditoría; por ahora solo lo imprimimos.
     */
    public void tomarEventoSeleccionado(EventoSismico evento) {
        System.out.println("Gestor: EventoSismico seleccionado → " + evento);
    }

    // ------------------------------------------
    // PASO 14-17: Confirmar / Rechazar / Derivar a experto
    // ------------------------------------------

    /**
     * Devuelve la lista de todos los Estados posibles (Confirmado, Rechazado, DerivadoAExperto, etc.)
     */
    public List<Estado> getEstadosPosibles() {
        return ObjectFactory.generarEstados();
    }

    /**
     * “cambiarEstadoEvento(evt, nuevoEstadoNombre)”: Cambia el estado del evento
     * a ese nombre (por ejemplo, “Confirmado”, “Rechazado” o “DerivadoAExperto”),
     * y setea la fechaHoraFin en ese instante.
     */
    public boolean cambiarEstadoEvento(EventoSismico evento, String nuevoEstadoNombre) {
        for (Estado est : ObjectFactory.generarEstados()) {
            if (est.getNombreEstado().equals(nuevoEstadoNombre)) {
                evento.setEstado(est);
                evento.setFechaHoraFin(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    /**
     * “actualizarDatosEvento(evt, nuevoAlcance, nuevoOrigen, nuevaMagnitud)”: 
     * Si el analista modifica desde el formulario alguno de estos valores, los asignamos acá.
     */
    public void actualizarDatosEvento(EventoSismico evento,
                                      AlcanceSismo nuevoAlcance,
                                      OrigenDeGeneracion nuevoOrigen,
                                      MagnitudRichter nuevaMagnitud) {
        evento.setAlcanceSismo(nuevoAlcance);
        evento.setOrigenGeneracion(nuevoOrigen);
        evento.setMagnitud(nuevaMagnitud);
    }

    /**
     * Paso 16: “confirmarAccionEvento(evt, accion)” valida que magnitud, alcance y origen no sean nulos,
     * y luego cambia el estado a “Confirmado” / “Rechazado” / “DerivadoAExperto” según corresponda.
     */
    public boolean confirmarAccionEvento(EventoSismico evento, String accion) {
        if (evento.tomarMagnitud() == null ||
            evento.getAlcanceSismo() == null ||
            evento.getOrigenGeneracion() == null) {
            return false;
        }

        String estadoDestino = switch (accion) {
            case "Confirmar evento"           -> "Confirmado";
            case "Rechazar evento"            -> "Rechazado";
            case "Solicitar revisión a experto" -> "DerivadoAExperto";
            default                            -> null;
        };
        if (estadoDestino == null) return false;

        return cambiarEstadoEvento(evento, estadoDestino);
    }

    // ------------------------------------------
    // Métodos auxiliares para llenar combos en la UI
    // ------------------------------------------

    public List<AlcanceSismo> getAlcances() {
        return baseHardcodeada.generarAlcances();
    }

    public List<OrigenDeGeneracion> getOrigenes() {
        return baseHardcodeada.generarOrígenes();
    }

    public List<MagnitudRichter> getMagnitudes() {
        return baseHardcodeada.generarMagnitudes();
    }

    /** 
     * Retorna la lista completa de todos los eventos. Puede usarse en otro contexto si se necesita.
     */
    public List<EventoSismico> getTodosLosEventos() {
        return eventos;
    }
}
