package org.example.modelos;

import java.util.ArrayList;

public class SerieTemporal {

    private ArrayList<MuestraSismica>   muestrasSismicas;
    private Estado                      estado;
    private String                      condicionAlarma;
    private String                      fechaHoraInicioRegistroMuestras;
    private String                      fechaHoraRegistro;
    private String                      frecuenciaMuestreo;
    private EstacionSismologica         estacionSismica;

    public SerieTemporal() {}

    public SerieTemporal(ArrayList<MuestraSismica> muestrasSismicas,
                         String frecuenciaMuestreo,
                         String fechaHoraRegistro,
                         String fechaHoraInicioRegistroMuestras,
                         String condicionAlarma,
                         Estado estado,
                         EstacionSismologica estacionSismica) {
        this.muestrasSismicas               = muestrasSismicas;
        this.frecuenciaMuestreo             = frecuenciaMuestreo;
        this.fechaHoraRegistro              = fechaHoraRegistro;
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.condicionAlarma                = condicionAlarma;
        this.estado                         = estado;
        this.estacionSismica                = estacionSismica;
    }

    public ArrayList<MuestraSismica> getMuestrasSismicas() {
        return muestrasSismicas;
    }
    public void setMuestrasSismicas(ArrayList<MuestraSismica> muestrasSismicas) {
        this.muestrasSismicas = muestrasSismicas;
    }

    public String getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }
    public void setFrecuenciaMuestreo(String frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

    public String getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }
    public void setFechaHoraRegistro(String fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public String getFechaHoraInicioRegistroMuestras() {
        return fechaHoraInicioRegistroMuestras;
    }
    public void setFechaHoraInicioRegistroMuestras(String fechaHoraInicioRegistroMuestras) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
    }

    public String getCondicionAlarma() {
        return condicionAlarma;
    }
    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstacionSismologica getEstacionSismica() {
        return estacionSismica;
    }
    public void setEstacionSismica(EstacionSismologica estacionSismica) {
        this.estacionSismica = estacionSismica;
    }
    
    @Override
    public String toString() {
        return "SerieTemporal{" +
               "estacionSismica=" + estacionSismica +
               ", condicionAlarma=" + condicionAlarma +
               ", fechaHoraInicioRegistroMuestras=" + fechaHoraInicioRegistroMuestras +
               ", fechaHoraRegistro=" + fechaHoraRegistro +
               ", frecuenciaMuestreo=" + frecuenciaMuestreo +
               ", estado=" + estado +
               ", muestrasSismicas=" + muestrasSismicas +
               '}';
    }
}
