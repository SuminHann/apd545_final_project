module com.example.apd545_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires javafx.graphics;


    opens com.example.apd545_final_project to javafx.fxml;
    exports com.example.apd545_final_project;
}