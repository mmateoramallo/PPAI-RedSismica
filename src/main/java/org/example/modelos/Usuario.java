package org.example.modelos;

import java.util.ArrayList;

public class Usuario {

    //Atributos de relaciones
    private ArrayList<Perfil> perfil;

    private Empleado empleado;

    private ArrayList<Suscripcion> suscripciones;

    //Atributos propios
    private String nombreUsuario;

    private String contraseña;

    public ArrayList<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(ArrayList<Perfil> perfil) {
        if(!perfil.isEmpty()){
            this.perfil = perfil;
        }else{
            System.out.println("Cargar al menos un perfil");
        }
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(ArrayList<Suscripcion> suscripciones) {
        if(suscripciones.isEmpty() || suscripciones.size() == 1){
            this.suscripciones = suscripciones;
        }else{
            System.out.println("Una suscripcion unicamente es valido");
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if(empleado != null){
            this.empleado = empleado;
        }else{
            System.out.println("Cargar al menos un empleado");
        }
    }

    public Usuario(){
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "perfil=" + perfil +
                ", empleado=" + empleado +
                ", suscripciones=" + suscripciones +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
