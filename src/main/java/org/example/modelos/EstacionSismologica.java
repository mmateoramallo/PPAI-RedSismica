package org.example.modelos;

import java.util.Date;

public class EstacionSismologica {

    private Integer codigoEstacion;
    private String  documentoCertificacionAdq;
    private Date    fechaSolicitudCertificacionAdq;
    private String  latitud;
    private String  longitud;
    private String  nombre;
    private Integer nroCertificacionAdquisicion;

    public EstacionSismologica() {}

    public EstacionSismologica(Integer codigoEstacion,
                               String nombre,
                               Integer nroCertificacionAdquisicion,
                               String longitud,
                               String latitud,
                               Date   fechaSolicitudCertificacionAdq,
                               String documentoCertificacionAdq) {
        this.codigoEstacion               = codigoEstacion;
        this.nombre                       = nombre;
        this.nroCertificacionAdquisicion  = nroCertificacionAdquisicion;
        this.longitud                     = longitud;
        this.latitud                      = latitud;
        this.fechaSolicitudCertificacionAdq = fechaSolicitudCertificacionAdq;
        this.documentoCertificacionAdq    = documentoCertificacionAdq;
    }

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

    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }
    public void setNroCertificacionAdquisicion(Integer nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    @Override
    public String toString() {
        return "EstacionSismologica{" +
               "codigoEstacion=" + codigoEstacion +
               ", nombre='" + nombre + '\'' +
               ", latitud='" + latitud + '\'' +
               ", longitud='" + longitud + '\'' +
               ", nroCertificacionAdquisicion=" + nroCertificacionAdquisicion +
               ", documentoCertificacionAdq='" + documentoCertificacionAdq + '\'' +
               '}';
    }
}
