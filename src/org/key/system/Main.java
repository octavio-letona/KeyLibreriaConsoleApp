
package org.key.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 *
 */
public class Main extends Application {

    private static Stage escenarioPrincipal;



    public static void cambiarVista(String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Parent root = loader.load();
        escenarioPrincipal.setScene(new Scene(root));

    }

    public static void main(String[] args) {
        launch(args);
    }

@Override
public void start(Stage stage) throws Exception {
    escenarioPrincipal = stage;
    cambiarVista("/org/key/view/CategoriaView.fxml"); // Abre directamente la vista de categorías
    stage.setTitle("Key Librería - Categorías");
    stage.show();
}
}