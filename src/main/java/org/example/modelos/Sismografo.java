package org.example.modelos;

import java.util.ArrayList;

public class Sismografo {

    private ModeloSismografo modelo;

    private String fechaAdquisicion;
    private Integer identificadorSismografo;
    private Integer nroSerie;

    private ArrayList<Reparacion> reparacion;

    public ModeloSismografo getModelo() {
        return modelo;
    }

    public void setModelo(ModeloSismografo modelo) {
        this.modelo = modelo;
    }

    public ArrayList<Reparacion> getReparacion() {
        return reparacion;
    }

    public void setReparacion(ArrayList<Reparacion> reparacion) {
        this.reparacion = reparacion;
    }

    public Integer getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(Integer nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Integer getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(Integer identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Sismografo(ModeloSismografo modelo, String fechaAdquisicion, Integer identificadorSismografo, ArrayList<Reparacion> reparacion, Integer nroSerie) {
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.reparacion = reparacion;
        this.nroSerie = nroSerie;
    }

    public Sismografo() {
    }

    @Override
    public String toString() {
        return "Sismografo{" +
                "modelo=" + modelo +
                ", fechaAdquisicion='" + fechaAdquisicion + '\'' +
                ", identificadorSismografo=" + identificadorSismografo +
                ", nroSerie=" + nroSerie +
                ", reparacion=" + reparacion +
                '}';
    }
}
