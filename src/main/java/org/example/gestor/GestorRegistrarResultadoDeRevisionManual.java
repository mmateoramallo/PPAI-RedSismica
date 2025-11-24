package org.example.gestor;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.example.modelos.*;
import java.util.List;
import java.util.stream.Collectors;
import org.example.dao.*;


public class GestorRegistrarResultadoDeRevisionManual {

    
    
    private final List<EventoSismico> eventos;
    private List<EventoSismico> eventosNoDet;
    
    
    // Simular sesion
    private Analista analistaLogueado;
    
    //DAOs
    private final EventoSismicoDAO eventosDAO;
    private final AlcanceSismoDAO alcanceDAO;
    private final OrigenDeGeneracionDAO origenDAO;
    private final MagnitudRichterDAO magnitudDAO;
    
    public GestorRegistrarResultadoDeRevisionManual() {
        this.analistaLogueado = new Analista("Mateo", "Ramallo", "mateo.j.ramallo@gmail.com", "94441");
        // Inicializar DAOs
        this.eventosDAO = new EventoSismicoDAO();
        this.alcanceDAO = new AlcanceSismoDAO();
        this.origenDAO = new OrigenDeGeneracionDAO();
        this.magnitudDAO = new MagnitudRichterDAO();
        this.eventos = eventosDAO.buscarTodos();
    }
    
    
    public void nuevaRevision() {
        
        System.out.println("Iniciando Revisión - Analista: " + analistaLogueado.tomarNombre());
        eventosNoDet = buscarEventoNoDet();
    }
    
    public List<EventoSismico> buscarEventoNoDet() {
        return eventos.stream()
                .filter(EventoSismico::sosAutoDetectado)
                .sorted((e1, e2) -> e2.tomarFechaHoraOcurrencia().compareTo(e1.tomarFechaHoraOcurrencia()))
                .collect(Collectors.toList());
    }

    
    
public void bloquearEventoSismico(EventoSismico evento) {
        // DELEGACIÓN: El evento delegará a su estado (AutoDetectado -> BloqueadoEnRevision)
        evento.bloquear();
        eventosDAO.actualizarEstado(evento);
    }

    public void rechazarEvento(EventoSismico eventoSeleccionado) {
        
        // Diagrama: getFechaHoraActual()
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        // Diagrama: rechazarEvento(..., fecha, ASlogueado)
        eventoSeleccionado.rechazarEvento(fechaHoraActual, this.analistaLogueado);
        eventosDAO.actualizarEstado(eventoSeleccionado);
    }

public boolean confirmarAccionEvento(EventoSismico evento, String accion) {
        if (evento.tomarMagnitud() == null || evento.getAlcanceSismo() == null || evento.getOrigenGeneracion() == null) {
            return false;
        }
        switch (accion) {
            case "Rechazar evento":
                // Llamamos al método que cumple con la firma del diagrama
                this.rechazarEvento(evento);
                eventosDAO.actualizarEstado(evento);
                return true;
                
            case "Confirmar evento":
                evento.confirmarEvento(LocalDateTime.now(), this.analistaLogueado);
                eventosDAO.actualizarEstado(evento);
                return true;
                
            default:
                return false;
        }
    }

    
    // Desacoplar UI
    public AlcanceSismo tomarAlcance(EventoSismico evento) { return evento.getAlcanceSismo(); }
    public ClasificacionSismo tomarClasificacion(EventoSismico evento) { return evento.getClasificacion(); }
    public OrigenDeGeneracion tomarOrigen(EventoSismico evento) { return evento.getOrigenGeneracion(); }
    public List<SerieTemporal> tomarSeriesTemporales(EventoSismico evento) { return evento.getSerieTemporal(); }

    public void pedirSeleccionEvento() {
        System.out.println("Gestor: por favor seleccione un evento de la tabla.");
    }

public void tomarEventoSeleccionado(EventoSismico evento) { 
        System.out.println("Seleccionado: " + evento); 
        //bloquearEventoSismico(evento); //Se dispara el bloqueo
    }

    public void actualizarDatosEvento(EventoSismico evento, AlcanceSismo a, OrigenDeGeneracion o, MagnitudRichter m) {
        evento.setAlcanceSismo(a);
        evento.setOrigenGeneracion(o);
        evento.setMagnitud(m);
    }

    // Getters para combos
public List<AlcanceSismo> getAlcances() {
        try {
            return alcanceDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // O manejar error
        }
    }

    public List<OrigenDeGeneracion> getOrigenes() {
        try {
            return origenDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<MagnitudRichter> getMagnitudes() {
        try {
            return magnitudDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}