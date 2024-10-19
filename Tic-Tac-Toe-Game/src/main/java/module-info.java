module lk.ijse.gdse.tictactoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.gdse.tictactoegame.controller to javafx.fxml;
    exports lk.ijse.gdse.tictactoegame;
}