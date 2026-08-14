package org.key.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal unificada
 *
 * @author Octavio Letona
 */
public class Main extends Application {

    // Se mantiene estático para poder cambiar las escenas desde cualquier controlador
    private static Stage escenarioPrincipal;

    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        Main.escenarioPrincipal = escenarioPrincipal;

        // 1. Iniciamos la aplicación cargando la vista de Inicio de Sesión
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/key/view/InicioSesionView.fxml"));
        Parent raiz = loader.load();
        Scene escena = new Scene(raiz);

        escenarioPrincipal.setTitle("Inicio de Sesión");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    /**
     * Método global para cambiar de pantallas (ej. pasar del Login al Menú)
     *
     * @param fxmlPath Ruta absoluta del archivo FXML
     */
    public static void cambiarVista(String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Parent root = loader.load();

        escenarioPrincipal.setScene(new Scene(root));
        escenarioPrincipal.centerOnScreen(); // Opcional: centra la ventana al cambiar de vista
   
    
    
    }
    
    
    public static Stage getEscenarioPrincipal() {
    return escenarioPrincipal;
}
    
}
