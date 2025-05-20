package org.example.UI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.modelos.EventoSismico;

import java.time.LocalDateTime;
import java.util.List;


public class InterfazRegistroRevisionManual {
    private TableView<EventoSismico> tablaEventos;
    private ComboBox<String> cbMagnitud, cbAlcance, cbOrigen;
    private Button btnRechazar, btnConfirmar, btnDerivar;
    private TextArea taDetalles;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Revisión Manual - Red Sísmica");

        // Contenedor principal
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // Panel superior: Lista de eventos no revisados
        VBox panelLista = new VBox(10);
        Label lblTitulo = new Label("Eventos Sísmicos No Revisados");
        tablaEventos = crearTablaEventos();
        panelLista.getChildren().addAll(lblTitulo, tablaEventos);
        root.setCenter(panelLista);

        // Panel inferior: Detalles y acciones
        VBox panelDetalles = new VBox(15);
        panelDetalles.setPadding(new Insets(15));
        panelDetalles.getChildren().addAll(
                crearSeccionDetalles(),
                crearSeccionAcciones()
        );
        root.setBottom(panelDetalles);

        // Cargar datos iniciales
        cargarEventosNoRevisados();

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    private TableView<EventoSismico> crearTablaEventos() {
        TableView<EventoSismico> tabla = new TableView<>();
        TableColumn<EventoSismico, LocalDateTime> colFecha = new TableColumn<>("Fecha/Hora");
        TableColumn<EventoSismico, String> colUbicacion = new TableColumn<>("Ubicación");
        TableColumn<EventoSismico, Double> colMagnitud = new TableColumn<>("Magnitud");
        // Configurar celdas (usar propiedades de EventoSismico)
        tabla.getColumns().addAll(colFecha, colUbicacion, colMagnitud);
        return tabla;
    }

    private GridPane crearSeccionDetalles() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Campos editables
        cbMagnitud = new ComboBox<>(FXCollections.observableArrayList("3.0", "4.0", "5.0"));
        cbAlcance = new ComboBox<>(FXCollections.observableArrayList("Local", "Regional", "Nacional"));
        cbOrigen = new ComboBox<>(FXCollections.observableArrayList("Tectónico", "Volcánico", "Artificial"));

        grid.addRow(0, new Label("Magnitud:"), cbMagnitud);
        grid.addRow(1, new Label("Alcance:"), cbAlcance);
        grid.addRow(2, new Label("Origen:"), cbOrigen);

        // Área de detalles adicionales
        taDetalles = new TextArea();
        taDetalles.setEditable(false);
        grid.add(new Label("Detalles:"), 0, 3);
        grid.add(taDetalles, 1, 3, 2, 1);

        return grid;
    }

    private HBox crearSeccionAcciones() {
        HBox hbox = new HBox(20);
        btnConfirmar = new Button("Confirmar Evento");
        btnRechazar = new Button("Rechazar Evento");
        btnDerivar = new Button("Derivar a Experto");

        // Estilo minimalista
        String estiloBoton = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
        btnConfirmar.setStyle(estiloBoton);
        btnRechazar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        btnDerivar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        hbox.getChildren().addAll(btnConfirmar, btnRechazar, btnDerivar);
        return hbox;
    }

    private void cargarEventosNoRevisados() {
        // Simulación de datos (integrar con lógica de negocio)
        List<EventoSismico> eventos = List.of(
                new EventoSismico(LocalDateTime.now(), "Córdoba", 4.5),
                new EventoSismico(LocalDateTime.now().minusHours(2), "Mendoza", 3.8)
        );
        tablaEventos.setItems(FXCollections.observableArrayList(eventos));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
