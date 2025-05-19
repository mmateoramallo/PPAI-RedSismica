package org.example.modelos;

public class Reparacion {
    private String comentariosReparacion;
    private String comentariosResolucionReparacion;
    private String fechaEnvioReparacion;
    private String fechaRespuestaReparacion;
    private Integer nroReparacion;

    public String getComentariosReparacion() {
        return comentariosReparacion;
    }

    public void setComentariosReparacion(String comentariosReparacion) {
        this.comentariosReparacion = comentariosReparacion;
    }

    public String getComentariosResolucionReparacion() {
        return comentariosResolucionReparacion;
    }

    public void setComentariosResolucionReparacion(String comentariosResolucionReparacion) {
        this.comentariosResolucionReparacion = comentariosResolucionReparacion;
    }

    public String getFechaEnvioReparacion() {
        return fechaEnvioReparacion;
    }

    public void setFechaEnvioReparacion(String fechaEnvioReparacion) {
        this.fechaEnvioReparacion = fechaEnvioReparacion;
    }

    public String getFechaRespuestaReparacion() {
        return fechaRespuestaReparacion;
    }

    public void setFechaRespuestaReparacion(String fechaRespuestaReparacion) {
        this.fechaRespuestaReparacion = fechaRespuestaReparacion;
    }

    public Integer getNroReparacion() {
        return nroReparacion;
    }

    public void setNroReparacion(Integer nroReparacion) {
        this.nroReparacion = nroReparacion;
    }

    public Reparacion(String comentariosReparacion, Integer nroReparacion, String fechaRespuestaReparacion, String fechaEnvioReparacion, String comentariosResolucionReparacion) {
        this.comentariosReparacion = comentariosReparacion;
        this.nroReparacion = nroReparacion;
        this.fechaRespuestaReparacion = fechaRespuestaReparacion;
        this.fechaEnvioReparacion = fechaEnvioReparacion;
        this.comentariosResolucionReparacion = comentariosResolucionReparacion;
    }

    public Reparacion() {
    }

    @Override
    public String toString() {
        return "Reparacion{" +
                "comentariosReparacion='" + comentariosReparacion + '\'' +
                ", comentariosResolucionReparacion='" + comentariosResolucionReparacion + '\'' +
                ", fechaEnvioReparacion='" + fechaEnvioReparacion + '\'' +
                ", fechaRespuestaReparacion='" + fechaRespuestaReparacion + '\'' +
                ", nroReparacion=" + nroReparacion +
                '}';
    }
}
