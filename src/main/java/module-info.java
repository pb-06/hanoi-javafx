module com.example.hanoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hanoi to javafx.fxml;
    exports com.example.hanoi;
}