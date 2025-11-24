package org.example.modelos;

import java.time.LocalDateTime;

public class BloqueadoEnRevision extends Estado {

    public BloqueadoEnRevision() {
        super("BloqueadoEnRevision", "El evento está siendo revisado por un analista");
    }

@Override
    public void rechazarEvento(EventoSismico evento, LocalDateTime fechaHoraActual, Analista responsable) {
        
        // 1. Mensaje: crearEstado() -> :Rechazado
        Estado nuevoEstado = Rechazado.crearEstado();

        // 2. Mensaje: new(estadoActual, fechaHoraInicio, ASlogueado) -> :CambioEstado
        // (Creamos el historial para el nuevo estado)
        CambioEstado nuevoCambio = new CambioEstado(nuevoEstado, fechaHoraActual, responsable);

        // 3. Mensaje: agregarCambioEstado(nuevoCambioEstado) -> :EventoSismico
        evento.agregarCambioEstado(nuevoCambio);

        // 4. Mensaje: setEstadoActual(estadoActual, fechaHoraActual) -> :EventoSismico
        evento.setEstadoActual(nuevoEstado);
        
        evento.setResponsable(responsable);
    }
    
    @Override
    public void confirmarEvento(EventoSismico evento, LocalDateTime fechaHoraActual, Analista responsable) {
        Estado nuevoEstado = new Confirmado(); // Asumiendo que existe la clase Confirmado
        CambioEstado nuevoCambio = new CambioEstado(nuevoEstado, fechaHoraActual, responsable);
        evento.agregarCambioEstado(nuevoCambio);
        evento.setEstadoActual(nuevoEstado);
        evento.setResponsable(responsable);
    }
}