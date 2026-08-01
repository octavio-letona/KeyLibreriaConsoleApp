
package org.key.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.key.dao.impl.CategoriaDAO;
import org.key.dao.impl.CategoriaDAOImpl;
import org.key.model.Categoria;
import org.key.system.Main;

/**
 *
 * @author informatica
 */
public class CategoriaController implements Initializable {

    @FXML
    private TextField txtID_categoria;
    @FXML
    private TextField txtNombre_categoria;

    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Categoria> tablaCategoria; // Tabla de entidad: categoria

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        listaCategorias.setAll(categoriaDAO.listarTodos());
        tablaCategoria.setItems(listaCategorias);
    }

    private void seleccionarFila() {
        tablaCategoria.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        // Asegúrate de que tu clase Categoria tenga los métodos getId() y getNombre_categoria()
                        txtID_categoria.setText(String.valueOf(newSelection.getId()));
                        txtNombre_categoria.setText(newSelection.getNombre_categoria());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            // CORREGIDO: Faltaba cerrar un paréntesis derecho en la condición
            if (txtID_categoria.getText().isEmpty() || txtNombre_categoria.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            // CORREGIDO: Se eliminó el uso incorrecto de 'tablaResultado' en el controlador
            Categoria categoria = new Categoria();
            categoria.setId(txtID_categoria.getText().trim());
            categoria.setNombre_categoria(txtNombre_categoria.getText().trim());

            if (categoriaDAO.crear(categoria)) {
                lblMensaje.setText("Categoria registrada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar la categoria.");
            }
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarFormulario();
        lblMensaje.setText("");
    }

    @FXML
    private void handleActualizar() {
        cargarTabla();
        lblMensaje.setText("Tabla actualizada.");
    }

    @FXML
    private void handleVolver() {
        try {
            Main.cambiarVista("/org/key/view/MenuPrincipal.fxml");
        } catch (Exception e) {
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtID_categoria.clear();
        txtNombre_categoria.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
