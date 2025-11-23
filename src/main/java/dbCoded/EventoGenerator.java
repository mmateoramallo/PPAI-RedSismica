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
            // Asegúrate que el string coincida con lo que tengas en ObjectFactory
            if (e.getNombreEstado().equals("AutoDetectado")) {
                estadoAutodetectado = e;
                break;
            }
        }
        
        // Validación por seguridad para que no explote si no lo encuentra
        if (estadoAutodetectado == null) {
            estadoAutodetectado = new Estado("Sistema", "AutoDetectado");
        }

        // ---------------------------------------------------------
        // 1) Generar primeros 5 eventos con estado AutoDetectado
        // ---------------------------------------------------------
        for (int i = 1; i <= 5; i++) {
            EventoSismico evento = new EventoSismico();
            
            // 1. Definimos fecha PRIMERO para usarla en el historial
            LocalDateTime fechaOcurrencia = LocalDateTime.now().minusMinutes(i * 5L);
            evento.setFechaHoraOcurrencia(fechaOcurrencia);

            // 2. Asignar clasificación aleatoria
            evento.setClasificacion(clasificaciones.get(random.nextInt(clasificaciones.size())));

            // 3. SETEAR ESTADO + HISTORIAL (Lo crucial que faltaba)
            // Seteamos el puntero actual
            evento.setEstado(estadoAutodetectado);
            
            // Simulamos que la DB trajo el historial: Creamos el CambioEstado inicial
            // Usamos el constructor nuevo que hicimos en el paso anterior: (Estado, Fecha)
            CambioEstado cambioInicial = new CambioEstado(estadoAutodetectado, fechaOcurrencia);
            
            // Lo agregamos a la lista del evento
            evento.getCambioEstado().add(cambioInicial);

            // 4. Asignamos resto de datos
            evento.setAlcanceSismo(alcances.get(random.nextInt(alcances.size())));
            evento.setOrigenGeneracion(origenes.get(random.nextInt(origenes.size())));
            evento.setMagnitud(magnitudes.get(random.nextInt(magnitudes.size())));
            
            evento.setLatitudEpicentro(-24 - i);
            evento.setLatitudHipocentro(-25 - i);
            evento.setLongitudEpicentro(-65 - i);
            evento.setLongitudHipocentro(-66 - i);
            evento.setSerieTemporal(factory.generarSeriesTemporalesParaEvento());
            evento.setValorMagnitud(4 + random.nextInt(4));
            
            eventos.add(evento);
        }

        // ---------------------------------------------------------
        // 2) Generar otros 10 eventos con estado aleatorio
        // ---------------------------------------------------------
        for (int i = 6; i <= 15; i++) {
            EventoSismico evento = new EventoSismico();
            
            // Fecha
            LocalDateTime fechaOcurrencia = LocalDateTime.now().minusMinutes(i * 5L);
            evento.setFechaHoraOcurrencia(fechaOcurrencia);

            evento.setClasificacion(clasificaciones.get(random.nextInt(clasificaciones.size())));
            
            // Estado aleatorio
            Estado estadoRandom = estados.get(random.nextInt(estados.size()));
            evento.setEstado(estadoRandom);

            // IMPORTANTE: Crear el historial para este estado random también
            CambioEstado cambioRandom = new CambioEstado(estadoRandom, fechaOcurrencia);
            
            // Si el estado NO es el inicial (ej. es "Confirmado"), en una DB real habría
            // un historial previo (ej: AutoDetectado -> Confirmado).
            // Para simplificar la simulación, asumimos que este es el estado actual vigente.
            evento.getCambioEstado().add(cambioRandom);

            // Resto de datos
            evento.setAlcanceSismo(alcances.get(random.nextInt(alcances.size())));
            evento.setOrigenGeneracion(origenes.get(random.nextInt(origenes.size())));
            evento.setMagnitud(magnitudes.get(random.nextInt(magnitudes.size())));
            evento.setLatitudEpicentro(-24 - i);
            evento.setLatitudHipocentro(-25 - i);
            evento.setLongitudEpicentro(-65 - i);
            evento.setLongitudHipocentro(-66 - i);
            evento.setSerieTemporal(factory.generarSeriesTemporalesParaEvento());
            evento.setValorMagnitud(4 + random.nextInt(4));
            
            eventos.add(evento);
        }

        return eventos;
    }
}