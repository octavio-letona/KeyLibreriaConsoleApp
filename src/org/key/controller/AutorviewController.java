/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.key.dao.impl.AutorDAO;
import org.key.dao.impl.AutorDAOImpl;
import org.key.model.Autor;
import org.key.system.Main;

public class AutorviewController implements Initializable {

    @FXML
    private TextField txtIdAutor;
    @FXML
    private TextField txtNombreAutor;
    @FXML
    private TextField txtApellidoAutor;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtBiografia;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Autor> tablaAutores; //Tabla de entidad: autor

    private final AutorDAO autorDAO = new AutorDAOImpl();
    private final ObservableList<Autor> listaAutores = FXCollections.observableArrayList(); //Entidad:Autor

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        listaAutores.setAll(autorDAO.listarTodos());
        tablaAutores.setItems(listaAutores);
    }

    private void seleccionarFila() {
        tablaAutores.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtIdAutor.setText(String.valueOf(newSelection.getIdAutor()));
                        txtNombreAutor.setText(newSelection.getNombreAutor());
                        txtApellidoAutor.setText(newSelection.getApellidoAutor());
                        txtNacionalidad.setText(newSelection.getNacionalidad());
                        txtBiografia.setText(newSelection.getBiografia());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            // El idAutor no es obligatorio evaluarlo aquí si en la base de datos es auto_increment
            if (txtNombreAutor.getText().isEmpty() || txtApellidoAutor.getText().isEmpty()
                    || txtNacionalidad.getText().isEmpty() || txtBiografia.getText().isEmpty()) {
                mostrarError("Los campos Nombre, Apellido, Nacionalidad y Biografía son obligatorios.");
                return;
            }

            Autor autor = new Autor();
            
            // Si el campo de ID tiene texto (ej. para una actualización futura), lo parseamos
            if (!txtIdAutor.getText().trim().isEmpty()) {
                autor.setIdAutor(Integer.parseInt(txtIdAutor.getText().trim()));
            }
            
            autor.setNombreAutor(txtNombreAutor.getText().trim());
            autor.setApellidoAutor(txtApellidoAutor.getText().trim());
            autor.setNacionalidad(txtNacionalidad.getText().trim());
            autor.setBiografia(txtBiografia.getText().trim());

            if (autorDAO.crear(autor)) {
                lblMensaje.setText("Autor registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el autor.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID del Autor debe ser un número válido.");
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
        txtIdAutor.clear();
        txtNombreAutor.clear();
        txtApellidoAutor.clear();
        txtNacionalidad.clear();
        txtBiografia.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
