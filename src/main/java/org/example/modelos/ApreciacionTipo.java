package org.example.modelos;

public class ApreciacionTipo {

    private String color;

    private String leyenda;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public ApreciacionTipo(String color, String leyenda) {
        this.color = color;
        this.leyenda = leyenda;
    }

    public ApreciacionTipo() {
    }

    @Override
    public String toString() {
        return "ApreciacionTipo{" +
                "color='" + color + '\'' +
                ", leyenda='" + leyenda + '\'' +
                '}';
    }
}
