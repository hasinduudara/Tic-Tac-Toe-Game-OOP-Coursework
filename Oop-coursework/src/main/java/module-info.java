module edu.ijse.oopcoursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ijse.oopcoursework.controller to javafx.fxml;
    exports edu.ijse.oopcoursework;
}