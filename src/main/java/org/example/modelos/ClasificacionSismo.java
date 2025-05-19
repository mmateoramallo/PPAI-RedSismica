package org.example.modelos;

public class ClasificacionSismo {

    private Integer kmProfundidadDesde;
    private Integer kmProfundidadHasta;

    private String nombre;

    public Integer getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public void setKmProfundidadDesde(Integer kmProfundidadDesde) {
        this.kmProfundidadDesde = kmProfundidadDesde;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public void setKmProfundidadHasta(Integer kmProfundidadHasta) {
        this.kmProfundidadHasta = kmProfundidadHasta;
    }

    public ClasificacionSismo(Integer kmProfundidadDesde, String nombre, Integer kmProfundidadHasta) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.nombre = nombre;
        this.kmProfundidadHasta = kmProfundidadHasta;
    }

    public ClasificacionSismo() {
    }

    @Override
    public String toString() {
        return "ClasificacionSismo{" +
                "kmProfundidadDesde=" + kmProfundidadDesde +
                ", kmProfundidadHasta=" + kmProfundidadHasta +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
