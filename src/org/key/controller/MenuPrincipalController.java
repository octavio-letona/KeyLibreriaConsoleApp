/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.key.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.key.system.Main;

//falta el extends initilize
public class MenuPrincipalController {

    @FXML
    private void handleClientes() {
        try {
            Main.cambiarVista("/org/key/view/ClienteView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de clientes:\n" + e.getMessage());
        }
        
    }

    @FXML
    private void handleEditorial() {
        try {
            // Verifica que este nombre de archivo coincida con mayúsculas y minúsculas exactas
            Main.cambiarVista("/org/key/view/EditorialView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de editoriales:\n" + e.toString());
        }
    }
    
    @FXML
    private void handleAutores() {
        try {
            // Verifica que este nombre de archivo coincida con mayúsculas y minúsculas exactas
            Main.cambiarVista("/org/key/view/CategoriaView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de editoriales:\n" + e.toString());
        }
    }
    
    
        @FXML
    private void handleCategorias() {
        try {
            // Verifica que este nombre de archivo coincida con mayúsculas y minúsculas exactas
            Main.cambiarVista("/org/key/view/CategoriaView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de editoriales:\n" + e.toString());
        }
    }

    @FXML
    private void handleNoDisponible() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Módulo no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Este módulo no está disponible aún.");
        alert.showAndWait();
    }

    @FXML
    private void handleSalir() {
        Platform.exit();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
