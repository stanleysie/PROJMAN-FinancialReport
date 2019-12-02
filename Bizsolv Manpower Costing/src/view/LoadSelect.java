package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Master;

public class LoadSelect implements View {

    @FXML
    Button load, backs;

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

        backs.setOnAction(event ->{
            Stage stage = (Stage) backs.getScene().getWindow();
            Menu menu = new Menu(stage, master);
        });

        backs.setOnMouseEntered(event -> {
            backs.setStyle("-fx-background-color: #535353");
        });

        backs.setOnMouseExited(event -> {
            backs.setStyle("-fx-background-color: #ef5350");
        });
    }

}
