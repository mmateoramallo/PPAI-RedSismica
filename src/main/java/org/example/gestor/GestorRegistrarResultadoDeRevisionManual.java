package org.example.gestor;

import dbCoded.EventoGenerator;
import dbCoded.ObjectFactory;
import org.example.modelos.Estado;
import org.example.modelos.EventoSismico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GestorRegistrarResultadoDeRevisionManual {

    private static final EventoGenerator generador = new EventoGenerator();
    private static final ObjectFactory baseHardcodeada = new ObjectFactory();

    // Paso 6
    public List<EventoSismico> buscarEventosSismicosNoRevisados() {
        List<EventoSismico> eventos = generador.generarEventosCompletos();
        return eventos.stream()
                .filter(e -> e.getEstado().getNombreEstado().equals("AutoDetectado"))
                .sorted((e1, e2) -> e2.getFechaHoraOcurrencia().compareTo(e1.getFechaHoraOcurrencia()))
                .collect(Collectors.toList());
    }

    // Paso 8 - Lista de estados posibles
    public List<Estado> getEstadosPosibles() {
        return ObjectFactory.generarEstados(); // es estático
    }

    // Cambiar el estado sin pasar String directamente en la UI
    public boolean cambiarEstadoEvento(EventoSismico evento, String nuevoEstadoNombre) {
        List<Estado> posibles = ObjectFactory.generarEstados();
        for (Estado est : posibles) {
            if (est.getNombreEstado().equals(nuevoEstadoNombre)) {
                evento.setEstado(est);
                evento.setFechaHoraFin(LocalDateTime.now());
                return true;
            }
        }
        return false; // no encontrado
    }
}
