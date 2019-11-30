package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Master;

public class Success implements View{

    @FXML
    private Label fileNames, fileLocation, fileTime, fileVersion;
    @FXML
    private Button generateNew, exit;

    private Stage stage;
    private Master master;

    public Success(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/SuccessView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setData();
    }

    public void initialize(){
        generateNew.setOnAction(event -> {
            Stage stage = (Stage) generateNew.getScene().getWindow();
            Menu menu = new Menu(stage, master);
        });

        generateNew.setOnMouseEntered(event -> {
            generateNew.setStyle("-fx-background-color: #535353");
        });

        generateNew.setOnMouseExited(event -> {
            generateNew.setStyle("-fx-background-color: #ef5350");
        });

        exit.setOnAction(event -> {
            Platform.exit();
        });

        exit.setOnMouseEntered(event -> {
            exit.setStyle("-fx-background-color: #535353");
        });

        exit.setOnMouseExited(event -> {
            exit.setStyle("-fx-background-color: #ef5350");
        });
    }

    private void setData() {
        fileNames.setText(master.getFileName() + " has been generated!");
        fileLocation.setText(master.getFileName() + " has been saved at " + master.getFileDestination());
        fileTime.setText(master.getFileTime());
        fileVersion.setText(master.getVersion());
    }
}
