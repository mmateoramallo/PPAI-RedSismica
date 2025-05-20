package org.example.modelos;

import java.util.Date;

public class EstacionSismologica {

    private Integer codigoEstacion;

    private String documentoCertificacionAdq;

    private Date fechaSolicitudCertificacionAdq;

    private String latitud;
    private String longitud;
    private String nombre;

    private Integer nroCertificacionAdquisicion;

    public Integer getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(Integer codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }

    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }

    public Date getFechaSolicitudCertificacionAdq() {
        return fechaSolicitudCertificacionAdq;
    }

    public void setFechaSolicitudCertificacionAdq(Date fechaSolicitudCertificacionAdq) {
        this.fechaSolicitudCertificacionAdq = fechaSolicitudCertificacionAdq;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(Integer nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    public EstacionSismologica(Integer codigoEstacion, String nombre, Integer nroCertificacionAdquisicion, String longitud, String latitud, Date fechaSolicitudCertificacionAdq, String documentoCertificacionAdq) {
        this.codigoEstacion = codigoEstacion;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fechaSolicitudCertificacionAdq = fechaSolicitudCertificacionAdq;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }

    public EstacionSismologica() {
    }

    @Override
    public String toString() {
        return "EstacionSismologica{" +
                "codigoEstacion=" + codigoEstacion +
                ", documentoCertificacionAdq='" + documentoCertificacionAdq + '\'' +
                ", fechaSolicitudCertificacionAdq=" + fechaSolicitudCertificacionAdq +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nroCertificacionAdquisicion=" + nroCertificacionAdquisicion +
                '}';
    }
}
