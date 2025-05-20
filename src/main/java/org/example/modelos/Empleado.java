package org.example.modelos;

public class Empleado {

    //Atributos de relaciones
    private Rol rol;

    //Atributos propios
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        if(!(rol == null)){
            this.rol = rol;
        }else {
            System.out.println("El rol no puede ser nulo");
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Empleado() {
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "rol=" + rol +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
