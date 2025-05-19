package org.example.modelos;

public class MotivoFueraServicio {

    private MotivoTipo motivoTipo;
    private String comentario;

    public MotivoTipo getMotivoTipo() {
        return motivoTipo;
    }

    public void setMotivoTipo(MotivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public MotivoFueraServicio(MotivoTipo motivoTipo, String comentario) {
        this.motivoTipo = motivoTipo;
        this.comentario = comentario;
    }

    public MotivoFueraServicio() {
    }

    @Override
    public String toString() {
        return "MotivoFueraServicio{" +
                "motivoTipo=" + motivoTipo +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
