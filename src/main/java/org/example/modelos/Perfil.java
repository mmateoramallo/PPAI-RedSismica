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
        if(!permisos.isEmpty()){
            this.permisos = permisos;
        }else{
            System.out.println("No se puede agregar el permiso");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Perfil(String nombre, ArrayList<Permiso> permisos, String descripcion) {
        if(!permisos.isEmpty()){
            this.nombre = nombre;
            this.permisos = permisos;
            this.descripcion = descripcion;
        }else{
            System.out.println("Se necesitan agregar permisos que no sean vacios");
        }
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
