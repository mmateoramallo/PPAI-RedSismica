// CAMBIOS EN GestorRegistrarResultadoDeRevisionManual.java
package org.example.gestor;

import dbCoded.EventoGenerator;
import dbCoded.ObjectFactory;
import org.example.modelos.Estado;
import org.example.modelos.EventoSismico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.example.modelos.AlcanceSismo;
import org.example.modelos.MagnitudRichter;
import org.example.modelos.OrigenDeGeneracion;

public class GestorRegistrarResultadoDeRevisionManual {

    private final EventoGenerator generador = new EventoGenerator();
    private final ObjectFactory baseHardcodeada = new ObjectFactory();

    // Lista fija de eventos generados al iniciar
    private final List<EventoSismico> eventos = generador.generarEventosCompletos();

    // Paso 6 - traer autodetectados que aún estén vigentes
    public List<EventoSismico> buscarEventosSismicosNoRevisados() {
        return eventos.stream()
                .filter(e -> e.getEstado().getNombreEstado().equals("AutoDetectado"))
                .sorted((e1, e2) -> e2.getFechaHoraOcurrencia().compareTo(e1.getFechaHoraOcurrencia()))
                .collect(Collectors.toList());
    }

    public List<Estado> getEstadosPosibles() {
        return ObjectFactory.generarEstados();
    }

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
    
    public void cargarDatosCompletosEvento(EventoSismico evento) {
        evento.setSerieTemporal(baseHardcodeada.generarSeriesTemporalesParaEvento());
    }

    // Permite volver a acceder todos los eventos generados en memoria
    public List<EventoSismico> getTodosLosEventos() {
        return eventos;
    }

    public List<AlcanceSismo> getAlcances() {
        return baseHardcodeada.generarAlcances();
    }

    public List<OrigenDeGeneracion> getOrigenes() {
        return baseHardcodeada.generarOrígenes();
    }

    public List<MagnitudRichter> getMagnitudes() {
        return baseHardcodeada.generarMagnitudes();
    }

    public void actualizarDatosEvento(EventoSismico evento, AlcanceSismo nuevoAlcance, OrigenDeGeneracion nuevoOrigen, MagnitudRichter nuevaMagnitud) {
        evento.setAlcanceSismo(nuevoAlcance);
        evento.setOrigenGeneracion(nuevoOrigen);
        evento.setMagnitud(nuevaMagnitud);
    }
    
    public boolean confirmarAccionEvento(EventoSismico evento, String accion) {
    if (evento.getMagnitud() == null || evento.getAlcanceSismo() == null || evento.getOrigenGeneracion() == null) {
        return false; // Validación fallida
    }
    String estadoDestino = switch (accion) {
        case "Confirmar evento" -> "Confirmado";
        case "Rechazar evento" -> "Rechazado";
        case "Solicitar revisión a experto" -> "DerivadoAExperto";
        default -> null;
    };
    if (estadoDestino == null) return false;
    return cambiarEstadoEvento(evento, estadoDestino);
}

}
