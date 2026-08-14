package org.key.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.key.dao.impl.CategoriaDAO;
import org.key.dao.impl.CategoriaDAOImpl;
import org.key.model.Categoria;
import org.key.system.Main;

public class CategoriaController implements Initializable {

    @FXML
    private TextField txtID_categoria;

    @FXML
    private TextField txtNombre_categoria;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Label lblMensaje;

    @FXML
     TableView<Categoria> tablaCategoria;

    @FXML
    private TableColumn<Categoria, Integer> colID;

    @FXML
    private TableColumn<Categoria, String> colNombre;

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();
    private FilteredList<Categoria> categoriasFiltradas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarTabla();
        seleccionarFila();
        configurarBuscador();
    }

    private void configurarTabla() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_categoria"));
        
        // CORRECCIÓN CLAVE: Vincular la lista Observable al TableView
        tablaCategoria.setItems(listaCategorias);
    }

    private void cargarTabla() {
        listaCategorias.setAll(categoriaDAO.listarTodos());
    }

    

    private void seleccionarFila() {
        tablaCategoria.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtID_categoria.setText(String.valueOf(newSelection.getId()));
                        txtNombre_categoria.setText(newSelection.getNombre_categoria());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNombre_categoria.getText().trim().isEmpty()) {
                mostrarError("El nombre de la categoría es obligatorio.");
                return;
            }

            Categoria categoria = new Categoria();
            categoria.setNombre_categoria(txtNombre_categoria.getText().trim());

            if (categoriaDAO.crear(categoria)) {
                lblMensaje.setText("Categoría registrada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar la categoría.");
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
        try {
            if (txtID_categoria.getText().isEmpty() || txtNombre_categoria.getText().trim().isEmpty()) {
                mostrarError("Seleccione una categoría para actualizar.");
                return;
            }

            Categoria categoria = new Categoria();
            categoria.setId(Integer.parseInt(txtID_categoria.getText().trim()));
            categoria.setNombre_categoria(txtNombre_categoria.getText().trim());

            if (categoriaDAO.actualizar(categoria)) {
                lblMensaje.setText("Categoría actualizada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo actualizar la categoría.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número válido.");
        } catch (Exception e) {
            mostrarError("Error al actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleEliminar() {
        try {
            if (txtID_categoria.getText().isEmpty()) {
                mostrarError("Seleccione una categoría de la tabla para eliminar.");
                return;
            }

            int id = Integer.parseInt(txtID_categoria.getText().trim());

            if (categoriaDAO.eliminar(id)) {
                lblMensaje.setText("Categoría eliminada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo eliminar la categoría.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID no es válido.");
        } catch (Exception e) {
            mostrarError("Error al eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleBuscar() {
        // La búsqueda ya se realiza automáticamente al escribir en txtBuscar gracias a configurarBuscador().
        // Este botón se puede usar para forzar el filtro si es necesario.
        if (txtBuscar != null && txtBuscar.getText() != null) {
            lblMensaje.setText("Resultados filtrados para: " + txtBuscar.getText());
        }
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
        if (txtBuscar != null) {
            txtBuscar.clear();
        }
        tablaCategoria.getSelectionModel().clearSelection();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}