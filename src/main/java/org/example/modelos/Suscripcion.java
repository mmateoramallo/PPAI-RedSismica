package org.example.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Suscripcion {
    //Atributos de relaciones
    private ArrayList<EstacionSismologica> estacionSismologica;

    private LocalDateTime fechaHoraFinSuscripcion;
    private LocalDateTime fechaHoraInicioSuscripcion;


    public ArrayList<EstacionSismologica> getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(ArrayList<EstacionSismologica> estacionSismologica) {
        if(estacionSismologica.isEmpty()){
            System.out.println("No se pueden registrar estaciones sismologicas vacias");
        }else{
            this.estacionSismologica = estacionSismologica;
        }
    }

    public LocalDateTime getFechaHoraInicioSuscripcion() {
        return fechaHoraInicioSuscripcion;
    }

    public void setFechaHoraInicioSuscripcion(LocalDateTime fechaHoraInicioSuscripcion) {
        this.fechaHoraInicioSuscripcion = fechaHoraInicioSuscripcion;
    }

    public LocalDateTime getFechaHoraFinSuscripcion() {
        return fechaHoraFinSuscripcion;
    }

    public void setFechaHoraFinSuscripcion(LocalDateTime fechaHoraFinSuscripcion) {
        this.fechaHoraFinSuscripcion = fechaHoraFinSuscripcion;
    }

    public Suscripcion() {
    }

    @Override
    public String toString() {
        return "Suscripcion{" +
                "estacionSismologica=" + estacionSismologica +
                ", fechaHoraFinSuscripcion=" + fechaHoraFinSuscripcion +
                ", fechaHoraInicioSuscripcion=" + fechaHoraInicioSuscripcion +
                '}';
    }
}
