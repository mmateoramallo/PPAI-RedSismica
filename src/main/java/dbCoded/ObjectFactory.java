/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbCoded;

import java.util.ArrayList;
import java.util.List;
import org.example.modelos.*;

/**
 *
 * @author mateo
 */
public class ObjectFactory {

    public ArrayList<ClasificacionSismo> generarClasificaciones() {
        ArrayList<ClasificacionSismo> clasificaciones = new ArrayList<>();
        clasificaciones.add(new ClasificacionSismo(0, "Muy superficial", 10));
        clasificaciones.add(new ClasificacionSismo(11, "Superficial", 70));
        clasificaciones.add(new ClasificacionSismo(71, "Intermedio", 300));
        clasificaciones.add(new ClasificacionSismo(301, "Profundo", 700));
        clasificaciones.add(new ClasificacionSismo(701, "Muy profundo", 1000));
        return clasificaciones;
    }

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

    public ArrayList<AlcanceSismo> generarAlcances() {
        ArrayList<AlcanceSismo> alcances = new ArrayList<>();
        alcances.add(new AlcanceSismo("Local", "Abarca una pequeña zona cercana al epicentro"));
        alcances.add(new AlcanceSismo("Regional", "Afecta varias provincias o regiones"));
        alcances.add(new AlcanceSismo("Nacional", "Cubre gran parte del territorio nacional"));
        alcances.add(new AlcanceSismo("Internacional", "Afecta países vecinos"));
        alcances.add(new AlcanceSismo("Global", "Evento extremadamente raro con impacto global"));
        return alcances;
    }

    public ArrayList<OrigenDeGeneracion> generarOrígenes() {
        ArrayList<OrigenDeGeneracion> origenes = new ArrayList<>();
        origenes.add(new OrigenDeGeneracion("Detectado por sistema automático", "Automático"));
        origenes.add(new OrigenDeGeneracion("Detectado y cargado manualmente por un analista", "Manual"));
        origenes.add(new OrigenDeGeneracion("Importado desde red internacional", "Importado"));
        origenes.add(new OrigenDeGeneracion("Simulado para pruebas", "Simulado"));
        origenes.add(new OrigenDeGeneracion("Evento corregido por operador", "Modificado"));
        return origenes;
    }

    public ArrayList<MagnitudRichter> generarMagnitudes() {
        ArrayList<MagnitudRichter> magnitudes = new ArrayList<>();
        magnitudes.add(new MagnitudRichter("Micro sismo", 2));
        magnitudes.add(new MagnitudRichter("Sismo menor", 4));
        magnitudes.add(new MagnitudRichter("Sismo moderado", 5));
        magnitudes.add(new MagnitudRichter("Sismo fuerte", 6));
        magnitudes.add(new MagnitudRichter("Sismo mayor", 7));
        return magnitudes;
    }

    public List<SerieTemporal> generarSeriesTemporalesParaEvento() {
        List<SerieTemporal> seriesTemporales = new ArrayList<>();
        List<Estado> estados = generarEstados();

        // Crear tipos de datos con unidad y umbral
        TipoDeDato tipoVelocidad = new TipoDeDato("Velocidad de onda", 150, "m/s");
        TipoDeDato tipoFrecuencia = new TipoDeDato("Frecuencia de onda", 100, "Hz");
        TipoDeDato tipoLongitud = new TipoDeDato("Longitud de onda", 500, "m");

        for (int i = 0; i < 2; i++) { // 2 series por evento
            ArrayList<MuestraSismica> muestras = new ArrayList<>();

            for (int j = 0; j < 3; j++) { // 3 muestras por serie
                ArrayList<DetalleMuestraSismica> detalles = new ArrayList<>();

                detalles.add(new DetalleMuestraSismica(tipoVelocidad, 120 + j));
                detalles.add(new DetalleMuestraSismica(tipoFrecuencia, 80 + j));
                detalles.add(new DetalleMuestraSismica(tipoLongitud, 450 + j));

                String fechaHoraMuestra = "2025-06-01T12:0" + j + ":00";
                MuestraSismica muestra = new MuestraSismica(detalles, fechaHoraMuestra);

                muestras.add(muestra);
            }

            Estado estado = estados.get(i % estados.size());

            SerieTemporal serie = new SerieTemporal(
                    muestras,
                    "100Hz",
                    "2025-06-01T12:10:00",
                    "2025-06-01T12:00:00",
                    i % 2 == 0 ? "Sin alarma" : "Alarma detectada",
                    estado
            );

            seriesTemporales.add(serie);
        }

        return seriesTemporales;
    }
}
