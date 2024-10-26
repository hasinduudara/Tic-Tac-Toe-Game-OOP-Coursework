package edu.ijse.oopcoursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Game.fxml"));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/game.png"))));
        stage.setTitle("Tic Tac Toe Game");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
