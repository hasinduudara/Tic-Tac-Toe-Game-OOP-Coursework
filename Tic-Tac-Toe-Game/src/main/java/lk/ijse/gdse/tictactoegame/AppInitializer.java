package lk.ijse.gdse.tictactoegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/GameView.fxml"));

        stage.setTitle("Tic Tac Toe Game");

        Scene scene = new Scene(new Group(load));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/game-Icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}