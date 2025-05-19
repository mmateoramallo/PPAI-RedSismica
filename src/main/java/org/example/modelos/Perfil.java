package org.example.modelos;

import java.util.ArrayList;

public class Perfil {
    private String nombre;

    private String descripcion;

    private ArrayList<Permiso> permisos;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Permiso> permisos) {
        this.permisos = permisos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Perfil(String nombre, ArrayList<Permiso> permisos, String descripcion) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.descripcion = descripcion;
    }

    public Perfil() {
    }

    @Override
    public String toString() {
        return "Pefil{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", permisos=" + permisos +
                '}';
    }
}
