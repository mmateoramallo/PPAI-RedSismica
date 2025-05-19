package org.example.modelos;

public class ReclamoGarantia {

    private Fabricante fabricante;

    private String comentario;
    private String fechaReclamo;
    private String fechaRespuesta;
    private Integer nroReclamo;
    private String respuestaFabricante;

    private Sismografo sismografo;


    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public String getRespuestaFabricante() {
        return respuestaFabricante;
    }

    public void setRespuestaFabricante(String respuestaFabricante) {
        this.respuestaFabricante = respuestaFabricante;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(String fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public Integer getNroReclamo() {
        return nroReclamo;
    }

    public void setNroReclamo(Integer nroReclamo) {
        this.nroReclamo = nroReclamo;
    }

    public Sismografo getSismografo() {
        return sismografo;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public ReclamoGarantia(Fabricante fabricante, String comentario, String fechaRespuesta, String fechaReclamo, Integer nroReclamo, String respuestaFabricante, Sismografo sismografo) {
        this.fabricante = fabricante;
        this.comentario = comentario;
        this.fechaRespuesta = fechaRespuesta;
        this.fechaReclamo = fechaReclamo;
        this.nroReclamo = nroReclamo;
        this.respuestaFabricante = respuestaFabricante;
        this.sismografo = sismografo;
    }

    public ReclamoGarantia() {
    }
}
