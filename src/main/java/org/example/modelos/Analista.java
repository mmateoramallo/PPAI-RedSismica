package org.example.modelos;

/**
 *
 * @author mateo
 */
public class Analista {
    
    private String apellido;
    private String nombre;
    private String mail;
    private String telefono;

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

    public String tomarTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Analista() {
    }
    
    public Analista(String nombre, String apellido, String mail, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Analista{" + "apellido=" + apellido + ", nombre=" + nombre + ", mail=" + mail + ", telefono=" + telefono + '}';
    }
    
    
    
}
