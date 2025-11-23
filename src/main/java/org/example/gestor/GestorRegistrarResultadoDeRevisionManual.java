package org.example.gestor;

import dbCoded.EventoGenerator;
import dbCoded.ObjectFactory;
import org.example.modelos.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorRegistrarResultadoDeRevisionManual {

    private final EventoGenerator generador = new EventoGenerator();
    private final ObjectFactory baseHardcodeada = new ObjectFactory();
    private final List<EventoSismico> eventos = generador.generarEventosCompletos();
    private List<EventoSismico> eventosNoDet;
    
    
    // Simular sesion
    private Analista analistaLogueado;
    
    public GestorRegistrarResultadoDeRevisionManual() {
        
        this.analistaLogueado = new Analista("Mateo", "Ramallo", "mateo.j.ramallo@gmail.com", "94441");
    }
    
    
    public void nuevaRevision() {
        
        System.out.println("Iniciando Revisión - Analista: " + analistaLogueado.tomarNombre());
        eventosNoDet = buscarEventoNoDet();
    }

    public List<EventoSismico> buscarEventoNoDet() {
        return eventos.stream()
                .filter(EventoSismico::sosAutoDetectado)
                .sorted((e1, e2) -> e2.tomarFechaHoraOcurrencia().compareTo(e1.tomarFechaHoraOcurrencia())) //Ordenar fecha hora ocurrencia
                .collect(Collectors.toList());
    }

    /**
     * Paso 8: “bloquearEventoSismico(evento)”.
     * Ahora cumple con el patrón EXPERTO: El Gestor DELEGA, no hace el trabajo sucio.
     * @param evento
     */
    public void bloquearEventoSismico(EventoSismico evento) {
        // 1. Delegar lógica de estado al dominio
        evento.bloquear();

        // 2. Asegurar series (esto sí puede hacerlo el gestor o delegarlo también)
        if (evento.getSerieTemporal() == null || evento.getSerieTemporal().isEmpty()) {
             evento.setSerieTemporal(baseHardcodeada.generarSeriesTemporalesParaEvento());
        }
    }

    /**
     * Paso 16: confirmarAccionEvento
     * CORREGIDO: Usamos switch para llamar a los métodos específicos del evento.
     */
public boolean confirmarAccionEvento(EventoSismico evento, String accion) {
        if (evento.tomarMagnitud() == null ||
            evento.getAlcanceSismo() == null ||
            evento.getOrigenGeneracion() == null) {
            return false;
        }

        switch (accion) {
            case "Confirmar evento":
                // Pasamos al analista simulando la recuperación de sesión
                evento.confirmar(this.analistaLogueado);
                return true;
                
            case "Rechazar evento":
                // Trazabilidad: tomarDatosResponsable -> rechazar
                evento.rechazar(this.analistaLogueado);
                return true;
                
            case "Solicitar revisión a experto":
                // evento.derivar(this.analistaLogueado);
                return true;
                
            default:
                return false;
        }
    }

    
    // Desacoplar UI
    public AlcanceSismo tomarAlcance(EventoSismico evento) { return evento.getAlcanceSismo(); }
    public ClasificacionSismo tomarClasificacion(EventoSismico evento) { return evento.getClasificacion(); }
    public OrigenDeGeneracion tomarOrigen(EventoSismico evento) { return evento.getOrigenGeneracion(); }
    public List<SerieTemporal> tomarSeriesTemporales(EventoSismico evento) { return evento.getSerieTemporal(); }

    public void pedirSeleccionEvento() {
        System.out.println("Gestor: por favor seleccione un evento de la tabla.");
    }

public void tomarEventoSeleccionado(EventoSismico evento) { 
        System.out.println("Seleccionado: " + evento); 
        bloquearEventoSismico(evento); //Se dispara el bloqueo
    }

    public void actualizarDatosEvento(EventoSismico evento, AlcanceSismo a, OrigenDeGeneracion o, MagnitudRichter m) {
        evento.setAlcanceSismo(a);
        evento.setOrigenGeneracion(o);
        evento.setMagnitud(m);
    }

    // Getters para combos
    public List<AlcanceSismo> getAlcances() { return baseHardcodeada.generarAlcances(); }
    public List<OrigenDeGeneracion> getOrigenes() { return baseHardcodeada.generarOrígenes(); }
    public List<MagnitudRichter> getMagnitudes() { return baseHardcodeada.generarMagnitudes(); }
}