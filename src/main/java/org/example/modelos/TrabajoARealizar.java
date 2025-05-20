package org.example.modelos;

import java.util.Date;

public class TrabajoARealizar {

    private TipoTrabajo definicionTrabajo;

    private String comentario;

    private Date fechaFinPrevista;

    private Date fechaFinReal;

    private Date fechaInicioPrevista;

    private Date fechaInicioReal;


    public TipoTrabajo getDefinicionTrabajo() {
        return definicionTrabajo;
    }

    public void setDefinicionTrabajo(TipoTrabajo definicionTrabajo) {
        this.definicionTrabajo = definicionTrabajo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public Date getFechaFinPrevista() {
        return fechaFinPrevista;
    }

    public void setFechaFinPrevista(Date fechaFinPrevista) {
        this.fechaFinPrevista = fechaFinPrevista;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public Date getFechaInicioPrevista() {
        return fechaInicioPrevista;
    }

    public void setFechaInicioPrevista(Date fechaInicioPrevista) {
        this.fechaInicioPrevista = fechaInicioPrevista;
    }

    public TrabajoARealizar(TipoTrabajo definicionTrabajo, Date fechaInicioReal, Date fechaInicioPrevista, Date fechaFinPrevista, String comentario, Date fechaFinReal) {
        this.definicionTrabajo = definicionTrabajo;
        this.fechaInicioReal = fechaInicioReal;
        this.fechaInicioPrevista = fechaInicioPrevista;
        this.fechaFinPrevista = fechaFinPrevista;
        this.comentario = comentario;
        this.fechaFinReal = fechaFinReal;
    }

    public TrabajoARealizar() {
    }

    @Override
    public String toString() {
        return "TrabajoARealizar{" +
                "definicionTrabajo=" + definicionTrabajo +
                ", comentario='" + comentario + '\'' +
                ", fechaFinPrevista=" + fechaFinPrevista +
                ", fechaFinReal=" + fechaFinReal +
                ", fechaInicioPrevista=" + fechaInicioPrevista +
                ", fechaInicioReal=" + fechaInicioReal +
                '}';
    }
}
