package dbCoded;

import org.example.modelos.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventoGenerator {

    private final ObjectFactory factory = new ObjectFactory();
    private final Random random = new Random();

    public List<EventoSismico> generarEventosCompletos() {
        List<EventoSismico> eventos = new ArrayList<>();

        List<ClasificacionSismo> clasificaciones = factory.generarClasificaciones();
        List<Estado> estados = factory.generarEstados();
        List<AlcanceSismo> alcances = factory.generarAlcances();
        List<OrigenDeGeneracion> origenes = factory.generarOrígenes();
        List<MagnitudRichter> magnitudes = factory.generarMagnitudes();

        // Encontramos el objeto Estado “AutoDetectado” en la lista
        Estado estadoAutodetectado = null;
        for (Estado e : estados) {
            if (e.getNombreEstado().equals("AutoDetectado")) {
                estadoAutodetectado = e;
                break;
            }
        }

        // 1) Generar primero 5 eventos con estado AutoDetectado
        for (int i = 1; i <= 5; i++) {
            EventoSismico evento = new EventoSismico();
            // Asignar clasificación aleatoria
            evento.setClasificacion(clasificaciones.get(random.nextInt(clasificaciones.size())));
            // Fijamos AutoDetectado
            evento.setEstado(estadoAutodetectado);
            // Asignamos aleatoriamente alcance, origen y magnitud
            evento.setAlcanceSismo(alcances.get(random.nextInt(alcances.size())));
            evento.setOrigenGeneracion(origenes.get(random.nextInt(origenes.size())));
            evento.setMagnitud(magnitudes.get(random.nextInt(magnitudes.size())));
            // Coordenadas y otros datos
            evento.setLatitudEpicentro(-24 - i);
            evento.setLatitudHipocentro(-25 - i);
            evento.setLongitudEpicentro(-65 - i);
            evento.setLongitudHipocentro(-66 - i);
            evento.setSerieTemporal(factory.generarSeriesTemporalesParaEvento());
            evento.setValorMagnitud(4 + random.nextInt(4));
            evento.setFechaHoraOcurrencia(LocalDateTime.now().minusMinutes(i * 5L));
            eventos.add(evento);
        }

        // 2) Generar los otros 10 eventos con estado cualquiera (incluida la posibilidad de repetir AutoDetectado)
        for (int i = 6; i <= 15; i++) {
            EventoSismico evento = new EventoSismico();
            evento.setClasificacion(clasificaciones.get(random.nextInt(clasificaciones.size())));
            // Estado aleatorio (puede salir AutoDetectado o cualquiera de la lista)
            evento.setEstado(estados.get(random.nextInt(estados.size())));
            evento.setAlcanceSismo(alcances.get(random.nextInt(alcances.size())));
            evento.setOrigenGeneracion(origenes.get(random.nextInt(origenes.size())));
            evento.setMagnitud(magnitudes.get(random.nextInt(magnitudes.size())));
            evento.setLatitudEpicentro(-24 - i);
            evento.setLatitudHipocentro(-25 - i);
            evento.setLongitudEpicentro(-65 - i);
            evento.setLongitudHipocentro(-66 - i);
            evento.setSerieTemporal(factory.generarSeriesTemporalesParaEvento());
            evento.setValorMagnitud(4 + random.nextInt(4));
            evento.setFechaHoraOcurrencia(LocalDateTime.now().minusMinutes(i * 5L));
            eventos.add(evento);
        }

        return eventos;
    }

    /*public static void main(String[] args) {
        EventoGenerator generador = new EventoGenerator();
        List<EventoSismico> eventos = generador.generarEventosCompletos();
        for (EventoSismico e : eventos) {
            System.out.println(e);
        }
    }*/
}
