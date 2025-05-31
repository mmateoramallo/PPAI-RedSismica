/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbCoded;

import org.example.modelos.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mateo
 */
public class EventoGenerator {
    
        
    private final ObjectFactory factory = new ObjectFactory();
    private final Random random = new Random();
    
    
        public  List<EventoSismico> generarEventosCompletos() {
        List<EventoSismico> eventos = new ArrayList<>();

        List<ClasificacionSismo> clasificaciones = factory.generarClasificaciones();
        List<Estado> estados = factory.generarEstados();
        List<AlcanceSismo> alcances = factory.generarAlcances();
        List<OrigenDeGeneracion> origenes = factory.generarOrígenes();
        List<MagnitudRichter> magnitudes = factory.generarMagnitudes();

        //Empleado analista = new Empleado("101", "María", "Analista", "maria@mail.com");

        for (int i = 1; i <= 10; i++) {
            EventoSismico evento = new EventoSismico();

            evento.setClasificacion(clasificaciones.get(random.nextInt(clasificaciones.size())));
            evento.setEstado(estados.get(random.nextInt(estados.size())));
            evento.setAlcanceSismo(alcances.get(random.nextInt(alcances.size())));
            evento.setOrigenGeneracion(origenes.get(random.nextInt(origenes.size())));
            evento.setMagnitud(magnitudes.get(random.nextInt(magnitudes.size())));
            //evento.setAnalistaSupervisor(analista);

            evento.setLatitudEpicentro(-24 - i);
            evento.setLatitudHipocentro(-25 - i);
            evento.setLongitudEpicentro(-65 - i);
            evento.setLongitudHipocentro(-66 - i);

            evento.setValorMagnitud(4 + random.nextInt(4));
            evento.setFechaHoraOcurrencia(LocalDateTime.now().minusMinutes(i * 5));

            eventos.add(evento);
        }

        return eventos;
    }
        
    
      public static void main(String[] args) {
        EventoGenerator generador = new EventoGenerator();
        List<EventoSismico> eventos = generador.generarEventosCompletos();
        
        
        for(EventoSismico e: eventos){
            System.out.println(e);
        }
    }
}
