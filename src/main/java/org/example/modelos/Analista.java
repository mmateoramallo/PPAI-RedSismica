package org.example.modelos;

/**
 *
 * @author mateo
 */
public class Analista {
    
    private String apellido;
    private String nombre;
    private String mail;
    private String legajo;

    private Integer idAnalista;
    
    private String contraseña;
    
    public String tomarApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String tomarNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String tomarMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String tomarLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Integer getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(Integer idAnalista) {
        this.idAnalista = idAnalista;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getLegajo() {
        return legajo;
    }
    
    
    
    public Analista() {
    }
    
    public Analista(String nombre, String apellido, String mail, String telefono, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.legajo = telefono;
        this.contraseña = contraseña;
    }


    
    public Analista(String nombre, String apellido, String mail, String legajo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.legajo = legajo;
    }
    
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    @Override
    public String toString() {
        return "Analista{" + "apellido=" + apellido + ", nombre=" + nombre + ", mail=" + mail + ", telefono=" + legajo + '}';
    }
    
    
    
}
