package org.example.modelos;

import java.util.ArrayList;

public class ModeloSismografo {

    private Fabricante fabricante;

    private ArrayList<String> caracteristicas;

    private String nombreModelo;

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public ArrayList<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ModeloSismografo(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public ModeloSismografo(Fabricante fabricante, String nombreModelo, ArrayList<String> caracteristicas) {
        this.fabricante = fabricante;
        this.nombreModelo = nombreModelo;
        this.caracteristicas = caracteristicas;
    }


    public ModeloSismografo() {
    }

    @Override
    public String
    toString() {
        return "ModeloSismografo{" +
                "fabricante=" + fabricante +
                ", caracteristicas=" + caracteristicas +
                ", nombreModelo='" + nombreModelo + '\'' +
                '}';
    }
}
