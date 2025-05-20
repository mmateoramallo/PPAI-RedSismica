package org.example.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrdenDeInspeccion {
    //Atributos de relaciones
    private Empleado empleado;

    private Estado estado;

    private EstacionSismologica estacionSismo;

    private ArrayList<TareaAsignada> tareaAsignada;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if(!(empleado == null)){
            this.empleado = empleado;
        }else{
            System.out.println("El empleado no puede ser nulo");
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if(!(estado == null)){
            this.estado = estado;
        }else{
            System.out.println("El estado no puede ser nulo");
        }
    }

    public EstacionSismologica getEstacionSismo() {
        return estacionSismo;
    }

    public void setEstacionSismo(EstacionSismologica estacionSismo) {
        if(!(estacionSismo == null)){
            this.estacionSismo = estacionSismo;
        }else{
            System.out.println("La estacion Sismologica no puede ser nula");
        }
    }

    public ArrayList<TareaAsignada> getTareaAsignada() {
        return tareaAsignada;
    }

    public void setTareaAsignada(ArrayList<TareaAsignada> tareaAsignada) {
        if(tareaAsignada.isEmpty()){
            System.out.println("Las tareas asignadas no pueden ser nulas");
        }else{
            this.tareaAsignada = tareaAsignada;
        }
    }

    private LocalDateTime fechaHoraCierre;

    private LocalDateTime fechaHoraFinalizacion;

    private LocalDateTime fechaHoraInicio;

    private Integer numeroOrden;

    private String descripcionCierre;


    public LocalDateTime getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(LocalDateTime fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public String getDescripcionCierre() {
        return descripcionCierre;
    }

    public void setDescripcionCierre(String descripcionCierre) {
        this.descripcionCierre = descripcionCierre;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public LocalDateTime getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDateTime fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public OrdenDeInspeccion() {
    }

    @Override
    public String toString() {
        return "OrdenDeInspeccion{" +
                "empleado=" + empleado +
                ", estado=" + estado +
                ", estacionSismo=" + estacionSismo +
                ", tareaAsignada=" + tareaAsignada +
                ", fechaHoraCierre=" + fechaHoraCierre +
                ", fechaHoraFinalizacion=" + fechaHoraFinalizacion +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", numeroOrden=" + numeroOrden +
                ", descripcionCierre='" + descripcionCierre + '\'' +
                '}';
    }
}
