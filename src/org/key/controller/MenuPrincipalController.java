package org.key.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.key.system.Main;

public class MenuPrincipalController {

    @FXML
    private void handleClientes() {
        try {
            Main.cambiarVista("/org/key/view/ClienteView.fxml");
        } catch (Exception e) {
            e.printStackTrace(); // <--- Imprime la traza completa en la consola
            mostrarError("Error al cargar la vista de clientes:\n" + e.toString());
        }
    }

    @FXML
    private void handleEditorial() {
        try {
            Main.cambiarVista("/org/key/view/EditorialView.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la vista de editorial:\n" + e.toString());
        }
    }

    @FXML
    private void handleAutores() {
        try {
            Main.cambiarVista("/org/key/view/autorview.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la vista de autores:\n" + e.toString());
        }
    }

    @FXML
    private void handleCategorias() {
        try {
            Main.cambiarVista("/org/key/view/CategoriaView.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la vista de categorías:\n" + e.toString());
        }
    }

    @FXML
    private void handleSalir() {
        Platform.exit();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Carga");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}