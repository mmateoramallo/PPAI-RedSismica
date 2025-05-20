package org.example.modelos;

import java.util.ArrayList;
import java.util.Date;

public class PlanConstruccionES {
    private ArrayList<TrabajoARealizar> trabajosARealizar;

    private EstacionSismologica estacionSismologica;

    private Empleado encargadoInstalacion;

    private Sismografo sismografoAsignado;

    //Atributos propios de cada clase
    private Integer codigoPlanConstruccion;

    private Date fechaFinalizacion;

    private Date fechaPrevistaInicio;

    private Date fechaProbableInicioPrueba;

    public ArrayList<TrabajoARealizar> getTrabajosARealizar() {
        return trabajosARealizar;
    }

    public void setTrabajosARealizar(ArrayList<TrabajoARealizar> trabajosARealizar) {
        if(!trabajosARealizar.isEmpty()) {
            this.trabajosARealizar = trabajosARealizar;
        }else{
            System.out.println("No se puede agregar trabajos vacios");
        }

    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public Integer getCodigoPlanConstruccion() {
        return codigoPlanConstruccion;
    }

    public void setCodigoPlanConstruccion(Integer codigoPlanConstruccion) {
        this.codigoPlanConstruccion = codigoPlanConstruccion;
    }

    public Sismografo getSismografoAsignado() {
        return sismografoAsignado;
    }

    public void setSismografoAsignado(Sismografo sismografoAsignado) {
        this.sismografoAsignado = sismografoAsignado;
    }

    public Empleado getEncargadoInstalacion() {
        return encargadoInstalacion;
    }

    public void setEncargadoInstalacion(Empleado encargadoInstalacion) {
        this.encargadoInstalacion = encargadoInstalacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Date getFechaProbableInicioPrueba() {
        return fechaProbableInicioPrueba;
    }

    public void setFechaProbableInicioPrueba(Date fechaProbableInicioPrueba) {
        this.fechaProbableInicioPrueba = fechaProbableInicioPrueba;
    }

    public Date getFechaPrevistaInicio() {
        return fechaPrevistaInicio;
    }

    public void setFechaPrevistaInicio(Date fechaPrevistaInicio) {
        this.fechaPrevistaInicio = fechaPrevistaInicio;
    }

    public PlanConstruccionES(ArrayList<TrabajoARealizar> trabajosARealizar, EstacionSismologica estacionSismologica, Empleado encargadoInstalacion, Integer codigoPlanConstruccion, Sismografo sismografoAsignado, Date fechaFinalizacion, Date fechaPrevistaInicio, Date fechaProbableInicioPrueba) {
        if(trabajosARealizar.isEmpty()) {
            this.trabajosARealizar = trabajosARealizar;
        }
        this.estacionSismologica = estacionSismologica;
        this.encargadoInstalacion = encargadoInstalacion;
        this.codigoPlanConstruccion = codigoPlanConstruccion;
        this.sismografoAsignado = sismografoAsignado;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaPrevistaInicio = fechaPrevistaInicio;
        this.fechaProbableInicioPrueba = fechaProbableInicioPrueba;
    }

    public PlanConstruccionES() {
    }

    @Override
    public String toString() {
        return "PlanConstruccionES{" +
                "trabajosARealizar=" + trabajosARealizar +
                ", estacionSismologica=" + estacionSismologica +
                ", encargadoInstalacion=" + encargadoInstalacion +
                ", sismografoAsignado=" + sismografoAsignado +
                ", codigoPlanConstruccion=" + codigoPlanConstruccion +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", fechaPrevistaInicio=" + fechaPrevistaInicio +
                ", fechaProbableInicioPrueba=" + fechaProbableInicioPrueba +
                '}';
    }
}
