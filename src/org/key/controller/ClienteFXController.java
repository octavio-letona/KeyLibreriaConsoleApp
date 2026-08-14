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
import org.key.dao.impl.ClienteDAO;
import org.key.dao.impl.ClienteDAOImpl;
import org.key.model.Cliente;
import org.key.system.Main;

public class ClienteFXController implements Initializable {

    @FXML
    private TextField txtBuscar; // Campo de búsqueda superior
    @FXML
    private TextField txtCui;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Cliente> tablaClientes; // Tabla de entidad: cliente

    @FXML
    private TableColumn colCui;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellido;
    @FXML
    private TableColumn colCorreo;

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(); // Entidad: Cliente

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarTabla();
        seleccionarFila();
    }

    private void configurarTabla() {
        colCui.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("cui"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("correoElectronico"));
    }

    private void cargarTabla() {
        listaClientes.setAll(clienteDAO.listarTodos());
        tablaClientes.setItems(listaClientes);
    }

    private void seleccionarFila() {
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtCui.setText(String.valueOf(newSelection.getCui()));
                        txtNombre.setText(newSelection.getNombre());
                        txtApellido.setText(newSelection.getApellido());
                        txtCorreo.setText(newSelection.getCorreoElectronico());
                    }
                });
    }

    // Alerta para la barra de búsqueda aún no implementada
    @FXML
    private void handleBuscar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Función no disponible");
        alert.setHeaderText(null);
        alert.setContentText("La función de búsqueda todavía no está agregada.");
        alert.showAndWait();
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtCui.getText().isEmpty() || txtNombre.getText().isEmpty()
                    || txtApellido.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setCui(Long.parseLong(txtCui.getText().trim()));
            cliente.setNombre(txtNombre.getText().trim());
            cliente.setApellido(txtApellido.getText().trim());
            cliente.setCorreoElectronico(txtCorreo.getText().trim());

            if (clienteDAO.crear(cliente)) {
                lblMensaje.setText("Cliente registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el cliente.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El CUI debe ser un número válido.");
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
        txtCui.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}