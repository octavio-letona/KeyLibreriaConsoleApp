package org.key.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.key.dao.impl.UsuarioDAO;
import org.key.model.Usuario;
import org.key.util.SecurityUtil;
import org.key.controller.AdminDashboradController;
import org.key.controller.EmpleadoDashboardController;
import org.key.system.Main;

public class InicioSesionController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Label lblMensaje;

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO = new UsuarioDAO();
        if (lblMensaje != null) {
            lblMensaje.setText("");
        }
    }

    private void handleNoDisponible() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Módulo no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Este módulo no está disponible aún.");
        alert.showAndWait();
    }

    @FXML
    public void eventoInicioSesion(ActionEvent evento) {
        String usuario = txtUsuario != null ? txtUsuario.getText() : "";
        String password = txtPassword != null ? txtPassword.getText() : "";

        // Verificación si los datos están vacíos
        if (usuario.trim().isEmpty() || password.trim().isEmpty()) {
            if (lblMensaje != null) {
                lblMensaje.setText("Por favor, complete todos sus datos.");
            }
            return;
        }

        // Datos completos
        String passwordHash = SecurityUtil.hashSHA256(password);

        // Llamar al DAO para iniciar sesión
        Usuario usuarioIniciado = usuarioDAO.iniciarSesion(usuario.trim(), passwordHash);

        if (usuarioIniciado != null) {
            if (lblMensaje != null) {
                lblMensaje.setText("Inicio correcto");
            }
            abrirDashboard(usuarioIniciado);
        } else {
            if (lblMensaje != null) {
                lblMensaje.setText("Usuario o contraseña incorrectos");
            }
        }
    }

    private void abrirDashboard(Usuario usuario) {
        if (usuario == null || usuario.getRol() == null) {
            if (lblMensaje != null) {
                lblMensaje.setText("Error: Rol de usuario no válido.");
            }
            return;
        }

        String rutaFXML = "";
        String tituloDashboard = "";

        switch (usuario.getRol().toLowerCase()) {
            case "admin":
                rutaFXML = "/org/key/view/MenuPrincipal.fxml";
                tituloDashboard = "Panel de Administración";
                break;
            case "empleado":
                rutaFXML = "/org/key/view/MenuPrincipal.fxml";
                tituloDashboard = "Panel de Empleado";
                break;
            case "cajero":
                rutaFXML = "/org/key/view/MenuPrincipal.fxml";
                tituloDashboard = "Panel de Cajero";
                break;
            default:
                if (lblMensaje != null) {
                    lblMensaje.setText("Rol no reconocido: " + usuario.getRol());
                }
                return;
        }

        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent raiz = cargadorFXML.load();
            Object controlador = cargadorFXML.getController();

            if (controlador instanceof AdminDashboradController) {
                ((AdminDashboradController) controlador).iniciarUsuario(usuario);
            } else if (controlador instanceof EmpleadoDashboardController) {
                ((EmpleadoDashboardController) controlador).iniciarUsuario(usuario);
            }

            // CORRECCIÓN: Reutilizamos el Stage principal en lugar de crear uno nuevo.
            // Así Main.escenarioPrincipal sigue apuntando a la ventana visible,
            // y Main.cambiarVista(...) podrá seguir cambiando la vista correctamente.
            Stage escenarioPrincipal = Main.getEscenarioPrincipal();
            escenarioPrincipal.setScene(new Scene(raiz));
            escenarioPrincipal.setTitle(tituloDashboard);
            escenarioPrincipal.centerOnScreen();

        } catch (Exception e) {
            System.err.println("Error al cargar la vista: " + rutaFXML + " - " + e.getMessage());
            e.printStackTrace();
            if (lblMensaje != null) {
                lblMensaje.setText("Error interno al abrir la vista.");
            }
        }
    }
}