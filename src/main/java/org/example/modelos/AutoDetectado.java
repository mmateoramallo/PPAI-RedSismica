package org.example.modelos;

import java.time.LocalDateTime;

public class AutoDetectado extends Estado {

    public AutoDetectado() {
        super("AutoDetectado", "El sismo fue detectado por sensores y espera revisión.");
    }

    @Override
    public void bloquear(EventoSismico evento) {
        // Lógica para pasar a BloqueadoEnRevision
        LocalDateTime ahora = LocalDateTime.now();
        
        // 1. Crear siguiente estado
        Estado nuevoEstado = new BloqueadoEnRevision();
        
        // 2. Crear historial (sin analista aun)
        CambioEstado cambio = new CambioEstado(nuevoEstado, ahora, null);
        
        // 3. Actualizar evento (Delegación)
        evento.agregarCambioEstado(cambio);
        evento.setEstadoActual(nuevoEstado);
    }
}