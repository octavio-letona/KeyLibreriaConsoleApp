
package org.key.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    @FXML TableColumn colID;
    @FXML TableColumn colNombre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
        configurarTabla();
    }
    
            private void configurarTabla() {
            //CellValueFactory, PropertyValueFactory
            //Valor de fabrica de celda
            colID.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("Id"));
            colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombre_categoria"));
 
    }

    private void cargarTabla() {
        listaCategorias.setAll(categoriaDAO.listarTodos());
        tablaCategoria.setItems(listaCategorias);
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
            if (txtNombre_categoria.getText().isEmpty()) {
                mostrarError("El nombre de la categoría es obligatorio.");
                return;
            }

            Categoria categoria = new Categoria();
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
        try {
            if (txtID_categoria.getText().isEmpty() || txtNombre_categoria.getText().isEmpty()) {
                mostrarError("Seleccione una categoría para actualizar.");
                return;
            }

            Categoria categoria = new Categoria();
            categoria.setId(Integer.parseInt(txtID_categoria.getText().trim()));
            categoria.setNombre_categoria(txtNombre_categoria.getText().trim());

            if (categoriaDAO.actualizar(categoria)) {
                lblMensaje.setText("Categoria actualizada exitosamente.");
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
    private void handleVolver() {
        try {
            // Asegúrate de que esta ruta sea exactamente la ruta donde tu compañero guardó el menú principal
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
}