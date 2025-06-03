package dbCoded;

import org.example.modelos.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ObjectFactory {

    private final Random random = new Random();

    // -----------------------------------------
    // 1) Generar Clasificaciones de sismo
    // -----------------------------------------
    public ArrayList<ClasificacionSismo> generarClasificaciones() {
        ArrayList<ClasificacionSismo> clasificaciones = new ArrayList<>();
        clasificaciones.add(new ClasificacionSismo(0,   "Muy superficial",     10));
        clasificaciones.add(new ClasificacionSismo(11,  "Superficial",         70));
        clasificaciones.add(new ClasificacionSismo(71,  "Intermedio",         300));
        clasificaciones.add(new ClasificacionSismo(301, "Profundo",          700));
        clasificaciones.add(new ClasificacionSismo(701, "Muy profundo",     1000));
        return clasificaciones;
    }

    // -----------------------------------------
    // 2) Generar Estados
    // -----------------------------------------
    public static ArrayList<Estado> generarEstados() {
        ArrayList<Estado> estadosHardcodeados = new ArrayList<>();
        estadosHardcodeados.add(new Estado("EventoSismico", "AutoDetectado"));
        estadosHardcodeados.add(new Estado("EventoSismico", "PendienteRevision"));
        estadosHardcodeados.add(new Estado("EventoSismico", "BloqueadoEnRevision"));
        estadosHardcodeados.add(new Estado("EventoSismico", "Confirmado"));
        estadosHardcodeados.add(new Estado("EventoSismico", "Rechazado"));
        estadosHardcodeados.add(new Estado("EventoSismico", "DerivadoAExperto"));
        return estadosHardcodeados;
    }

    // -----------------------------------------
    // 3) Generar Alcances de Sismo
    // -----------------------------------------
    public ArrayList<AlcanceSismo> generarAlcances() {
        ArrayList<AlcanceSismo> alcances = new ArrayList<>();
        alcances.add(new AlcanceSismo("Local",           "Abarca una pequeña zona cercana al epicentro"));
        alcances.add(new AlcanceSismo("Regional",        "Afecta varias provincias o regiones"));
        alcances.add(new AlcanceSismo("Nacional",        "Cubre gran parte del territorio nacional"));
        alcances.add(new AlcanceSismo("Internacional",   "Afecta países vecinos"));
        alcances.add(new AlcanceSismo("Global",          "Evento extremadamente raro con impacto global"));
        return alcances;
    }

    // -----------------------------------------
    // 4) Generar Orígenes de Generación
    // -----------------------------------------
    public ArrayList<OrigenDeGeneracion> generarOrígenes() {
        ArrayList<OrigenDeGeneracion> origenes = new ArrayList<>();
        origenes.add(new OrigenDeGeneracion("Detectado por sistema automático", "Automático"));
        origenes.add(new OrigenDeGeneracion("Detectado y cargado manualmente por un analista", "Manual"));
        origenes.add(new OrigenDeGeneracion("Importado desde red internacional", "Importado"));
        origenes.add(new OrigenDeGeneracion("Simulado para pruebas", "Simulado"));
        origenes.add(new OrigenDeGeneracion("Evento corregido por operador", "Modificado"));
        return origenes;
    }

    // -----------------------------------------
    // 5) Generar Magnitudes (Richter)
    // -----------------------------------------
    public ArrayList<MagnitudRichter> generarMagnitudes() {
        ArrayList<MagnitudRichter> magnitudes = new ArrayList<>();
        magnitudes.add(new MagnitudRichter("Micro sismo", 2));
        magnitudes.add(new MagnitudRichter("Sismo menor", 4));
        magnitudes.add(new MagnitudRichter("Sismo moderado", 5));
        magnitudes.add(new MagnitudRichter("Sismo fuerte", 6));
        magnitudes.add(new MagnitudRichter("Sismo mayor", 7));
        return magnitudes;
    }

    // -----------------------------------------
    // 6) Generar Estaciones Sismológicas
    // -----------------------------------------
    public ArrayList<EstacionSismologica> generarEstaciones() {
        ArrayList<EstacionSismologica> estaciones = new ArrayList<>();
        // Creamos varias estaciones de ejemplo con coordenadas y fecha/docus ficticios
        estaciones.add(new EstacionSismologica(
                1, "Estación Norte", 1001, "-70.0", "-30.0", new Date(), "DOC-EST-001"));
        estaciones.add(new EstacionSismologica(
                2, "Estación Sur",   1002, "-71.0", "-31.0", new Date(), "DOC-EST-002"));
        estaciones.add(new EstacionSismologica(
                3, "Estación Este",  1003, "-69.5", "-29.8", new Date(), "DOC-EST-003"));
        estaciones.add(new EstacionSismologica(
                4, "Estación Oeste", 1004, "-72.2", "-32.1", new Date(), "DOC-EST-004"));
        estaciones.add(new EstacionSismologica(
                5, "Estación Central",1005, "-70.5", "-30.5", new Date(), "DOC-EST-005"));
        return estaciones;
    }

    // -----------------------------------------
    // 7) Generar Series Temporales Para un Evento
    // -----------------------------------------
    /**
     * Crea una lista de SeriesTemporales (con sus MuestraSismica y DetalleMuestraSismica),
     * y a cada serie le asigna una estación elegida aleatoriamente de la lista generada en generarEstaciones().
     */
    public List<SerieTemporal> generarSeriesTemporalesParaEvento() {
        List<SerieTemporal> seriesTemporales = new ArrayList<>();
        List<Estado>         estados        = generarEstados();
        List<EstacionSismologica> estaciones = generarEstaciones();

        // Creamos 2 series por evento, cada una asignada a una estación al azar
        for (int i = 0; i < 2; i++) {
            // ---- 7.1) Generar 3 muestras por serie ----
            ArrayList<MuestraSismica> muestras = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                ArrayList<DetalleMuestraSismica> detalles = new ArrayList<>();

                // Creación de 3 tipos de dato: Velocidad, Frecuencia, Longitud
                TipoDeDato tipoVelocidad  = new TipoDeDato("Velocidad de onda", 150, "m/s");
                TipoDeDato tipoFrecuencia = new TipoDeDato("Frecuencia de onda", 100, "Hz");
                TipoDeDato tipoLongitud   = new TipoDeDato("Longitud de onda", 500, "m");

                // Para cada detalle, asignamos un valor de ejemplo (120+j, 80+j, 450+j)
                detalles.add(new DetalleMuestraSismica(tipoVelocidad,  120 + j));
                detalles.add(new DetalleMuestraSismica(tipoFrecuencia, 80  + j));
                detalles.add(new DetalleMuestraSismica(tipoLongitud,   450 + j));

                // Creamos la muestra con una hora simulada
                String fechaHoraMuestra = "2025-06-01T12:0" + j + ":00";
                MuestraSismica muestra = new MuestraSismica(detalles, fechaHoraMuestra);
                muestras.add(muestra);
            }

            // ---- 7.2) Elegir un Estado al azar de la lista de estados ----
            Estado estadoSerie = estados.get(random.nextInt(estados.size()));

            // ---- 7.3) Elegir una Estación al azar de la lista de estaciones ----
            EstacionSismologica estacionElegida = estaciones.get(random.nextInt(estaciones.size()));

            // ---- 7.4) Crear la SerieTemporal con todos sus datos y asignar la estación ----
            SerieTemporal serie = new SerieTemporal();
            serie.setMuestrasSismicas(muestras);
            serie.setFrecuenciaMuestreo("100Hz");
            serie.setFechaHoraInicioRegistroMuestras("2025-06-01T12:00:00");
            serie.setFechaHoraRegistro("2025-06-01T12:10:00");
            serie.setCondicionAlarma(i % 2 == 0 ? "Sin alarma" : "Alarma detectada");
            serie.setEstado(estadoSerie);
            serie.setEstacionSismica(estacionElegida);

            seriesTemporales.add(serie);
        }

        return seriesTemporales;
    }
}
