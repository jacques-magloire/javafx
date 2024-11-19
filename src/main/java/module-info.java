module com.example.javafxediteur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens etu.ecole.ensicaen.javafxediteur to javafx.fxml;
    exports etu.ecole.ensicaen.javafxediteur;
}