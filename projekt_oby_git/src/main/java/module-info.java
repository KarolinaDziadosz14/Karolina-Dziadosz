module com.example.projekt_oby_git {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekt_oby_git to javafx.fxml;
    exports com.example.projekt_oby_git;
}