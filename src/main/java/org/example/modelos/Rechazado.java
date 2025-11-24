package org.example.modelos;

public class Rechazado extends Estado {

    public Rechazado() {
        super("Rechazado", "El evento ha sido descartado por el analista");
    }

    public static Estado crearEstado() {
        return new Rechazado();
    }
}
