/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import org.key.dao.impl.EditorialDAO;
import org.key.dao.impl.EditorialDAOImpl;
import org.key.model.Editorial;
import org.key.system.Main;

public class EditorialFXController implements Initializable {

    @FXML
    private TextField txtNitEditorial;
    @FXML
    private TextField txtNombreEditorial;
    @FXML
    private TextField txtTelefonoEditorial;
    @FXML
    private TextField txtDireccionEditorial;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Editorial> tablaEditorial;

      @FXML
    TableColumn colNit;
    @FXML
    TableColumn colNombre;
    @FXML
    TableColumn colTelefono;
    @FXML
    TableColumn colDirecion;
    
    
    private final EditorialDAO editorialDAO = new EditorialDAOImpl();
    private final ObservableList<Editorial> listaEditoriales = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarTabla();
        seleccionarFila();

    }
    

    private void cargarTabla() {
        listaEditoriales.setAll(editorialDAO.listarTodos());
        tablaEditorial.setItems(listaEditoriales);
    }

     private void configurarTabla() {

        colNit.setCellValueFactory(new PropertyValueFactory<Editorial, Long>("nit"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Editorial, String>("nombreEditorial"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Editorial, String>("apellido"));
        colDirecion.setCellValueFactory(new PropertyValueFactory<Editorial, String>("correoElectronico"));

    }

    
    private void seleccionarFila() {
        tablaEditorial.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtNitEditorial.setText(String.valueOf(newSelection.getNit()));
                        txtNombreEditorial.setText(newSelection.getNombreEditorial());
                        txtTelefonoEditorial.setText(newSelection.getTelefonoEditorial());
                        txtDireccionEditorial.setText(newSelection.getDireccionEditorial());
                    }
                }
        );
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNitEditorial.getText().isEmpty() || txtNombreEditorial.getText().isEmpty()
                    || txtTelefonoEditorial.getText().isEmpty() || txtDireccionEditorial.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Editorial editorial = new Editorial();
            editorial.setNit((txtNitEditorial.getText().trim()));
            editorial.setNombreEditorial(txtNombreEditorial.getText().trim());
            editorial.setTelefonoEditorial(txtTelefonoEditorial.getText().trim());
            editorial.setDireccionEditorial(txtDireccionEditorial.getText().trim());

            if (editorialDAO.crear(editorial)) {
                lblMensaje.setText("Editorial registrada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar la editorial.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El NIT debe ser un número válido.");
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
        txtNitEditorial.clear();
        txtNombreEditorial.clear();
        txtDireccionEditorial.clear();
        txtTelefonoEditorial.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
