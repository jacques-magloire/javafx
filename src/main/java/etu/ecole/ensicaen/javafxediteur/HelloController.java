package etu.ecole.ensicaen.javafxediteur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelloController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab;

    @FXML
    private Tab tab1;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem Fichier;

    @FXML
    private MenuItem Nouveau;

    @FXML
    private MenuItem Enregistrer;

    @FXML
    private MenuItem Fermer;

    @FXML
    private TextArea textArea;

    @FXML
    private WebView webView;

    private Boolean saisidetexte= false;

    @FXML
    private Label Caractereslabel;
    private Boolean comptenombredecaracteres= true;

    @FXML
    private Label Ligneslabel;
    private Boolean comptenombredelignes= true;

    @FXML

    private void initialize() {

        //Menu saveMenuItem = new Menu("Enregistrer");
        Enregistrer.setDisable(true);
        WebEngine webEngine = webView.getEngine();
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            webEngine.loadContent(newValue);
            saisidetexte = true;
            tab.setText(" exemple.html (*)");

            int numberOfCharacters = newValue.length();
            Caractereslabel.setText("Caracteres: "+ numberOfCharacters);

            int numberOfLines = textArea.getText().split("\n").length;
            Ligneslabel.setText("Lignes: "+ numberOfLines);
            Enregistrer.setDisable(false);

        });
        tabPane.getTabs().add(tab1);
       tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue == tab1) {
               Tab newtable  = handleNouveau();
               tabPane.getTabs().add(tabPane.getTabs().size()-1, newtable);
               tabPane.getSelectionModel().select(newtable);
           }
       });
    }



    @FXML
    private void handlecopier() {
        textArea.copy();
    }

    @FXML
    private void handlecoller() {
    textArea.paste();
    }

    @FXML
    private void handlecouper() {
        textArea.cut();
    }

    private  Tab handleNouveau() {

        int tabcount = tabPane.getTabs().size();

        Tab newtab = new Tab("exemple.html" + tabcount);
        newtab.setClosable(true);

        AnchorPane anchorPane = new AnchorPane();
        SplitPane splitPane = new SplitPane();
        TextArea textArea = new TextArea();
        WebView webView1 = new WebView();
        WebEngine webEngine = webView1.getEngine();

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            webEngine.loadContent(newValue);
            saisidetexte = true;
            tab.setText(" exemple.html (*)");

            int numberOfCharacters = newValue.length();
            Caractereslabel.setText("Caracteres: "+ numberOfCharacters);

            int numberOfLines = textArea.getText().split("\n").length;
            Ligneslabel.setText("Lignes: "+ numberOfLines);
            Enregistrer.setDisable(false);

        });

        splitPane.getItems().addAll(textArea,webView1);

        AnchorPane.setTopAnchor(textArea,0.0);
        AnchorPane.setBottomAnchor(textArea,0.0);
        AnchorPane.setLeftAnchor(textArea,0.0);
        AnchorPane.setRightAnchor(textArea,0.0);


        anchorPane.getChildren().add(splitPane);

        newtab.setContent(anchorPane);

        return newtab;
    }

}