package org.example.modelos;

import java.util.ArrayList;

public class Sismografo {

    private ModeloSismografo modelo;

    private String fechaAdquisicion;
    private Integer identificadorSismografo;
    private Integer nroSerie;

    private ArrayList<Reparacion> reparacion;

    private EstacionSismologica estacionSismologica;

    private ArrayList<CambioEstado> cambioEstado;

    private ArrayList<SerieTemporal> serieTemporal;

    public Sismografo(ModeloSismografo modelo, String fechaAdquisicion, Integer identificadorSismografo, Integer nroSerie, ArrayList<Reparacion> reparacion, EstacionSismologica estacionSismologica, ArrayList<CambioEstado> cambioEstado, ArrayList<SerieTemporal> serieTemporal) {
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.reparacion = reparacion;
        this.estacionSismologica = estacionSismologica;
        this.cambioEstado = cambioEstado;
        this.serieTemporal = serieTemporal;
    }

    public Sismografo() {
    }

    public ModeloSismografo getModelo() {
        return modelo;
    }

    public void setModelo(ModeloSismografo modelo) {
        this.modelo = modelo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Integer getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(Integer identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public Integer getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(Integer nroSerie) {
        this.nroSerie = nroSerie;
    }

    public ArrayList<Reparacion> getReparacion() {
        return reparacion;
    }

    public void setReparacion(ArrayList<Reparacion> reparacion) {
        this.reparacion = reparacion;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public ArrayList<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(ArrayList<SerieTemporal> serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    @Override
    public String toString() {
        return "Sismografo{" +
                "modelo=" + modelo +
                ", fechaAdquisicion='" + fechaAdquisicion + '\'' +
                ", identificadorSismografo=" + identificadorSismografo +
                ", nroSerie=" + nroSerie +
                ", reparacion=" + reparacion +
                ", estacionSismologica=" + estacionSismologica +
                ", cambioEstado=" + cambioEstado +
                ", serieTemporal=" + serieTemporal +
                '}';
    }
}
