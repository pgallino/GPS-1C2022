package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.constructor_juego.JuegoDirector;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.view.ContenedorMenu;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        JuegoDirector director = new JuegoDirector();
        Scene scene = new Scene(new ContenedorMenu(director));
        stage.setScene(scene);
        stage.show();
    }
}