module com.example.apd545_final_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apd545_final_project to javafx.fxml;
    exports com.example.apd545_final_project;
}