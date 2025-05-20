package org.example.modelos;

import java.util.ArrayList;
import java.util.Date;

public class TareaAsignada {

    private ArrayList<ApreciacionTipo> apreciacion;


    private TipoTareaInspeccion tarea;

    //Atributos propios
    private String comentario;

    private Date fechaHoraRealizacion;


    public ArrayList<ApreciacionTipo> getApreciacion() {
        return apreciacion;
    }

    public void setApreciacion(ArrayList<ApreciacionTipo> apreciacion) {
        //Verificar que puede ser 0 o 1
        if(apreciacion.isEmpty() || apreciacion.size() == 1){
            this.apreciacion = apreciacion;
        }
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaHoraRealizacion() {
        return fechaHoraRealizacion;
    }

    public void setFechaHoraRealizacion(Date fechaHoraRealizacion) {
        this.fechaHoraRealizacion = fechaHoraRealizacion;
    }


    public TipoTareaInspeccion getTarea() {
        return tarea;
    }

    public void setTarea(TipoTareaInspeccion tarea) {
        this.tarea = tarea;
    }

    public TareaAsignada(ArrayList<ApreciacionTipo> apreciacion, String comentario, Date fechaHoraRealizacion, TipoTareaInspeccion tarea) {
        if(apreciacion.isEmpty() || apreciacion.size() == 1){
            this.apreciacion = apreciacion;
            this.comentario = comentario;
            this.fechaHoraRealizacion = fechaHoraRealizacion;
            this.tarea = tarea;
        } else{
            System.out.println("Tiene que haber al menos una apreciacion");
        }
    }

    @Override
    public String toString() {
        return "TareaAsignada{" +
                "apreciacion=" + apreciacion +
                ", comentario='" + comentario + '\'' +
                ", fechaHoraRealizacion=" + fechaHoraRealizacion +
                '}';
    }
}
