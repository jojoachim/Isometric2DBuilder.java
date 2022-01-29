module com.example.isometric2dmotor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.isometric2dmotor to javafx.fxml;
    exports com.example.isometric2dmotor;
}