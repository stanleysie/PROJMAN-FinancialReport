package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Master;

public class LoadSelect implements View {

    @FXML
    Button load, menu, back;
    
    @FXML
    Label employee;

    private Stage stage;
    private Master master;

    public LoadSelect(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/LoadSelectView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize() {
        load.setDefaultButton(true);
        load.setOnAction(event -> {
            Stage stage = (Stage) load.getScene().getWindow();
            LoadFile loadFile = new LoadFile(stage, master);
        });

        load.setOnMouseEntered(event -> {
            load.setStyle("-fx-background-color: #535353");
        });

        load.setOnMouseExited(event -> {
            load.setStyle("-fx-background-color: #ef5350");
        });

        menu.setOnAction(event ->{
            Stage stage = (Stage) menu.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage, master);
        });

        menu.setOnMouseEntered(event -> {
            menu.setStyle("-fx-background-color: #535353");
        });

        menu.setOnMouseExited(event -> {
            menu.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{


        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #ef5350");
            back.setStyle("-fx-color: white");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: white");
            back.setStyle("-fx-color: #ef5350");
        });
    }

}
