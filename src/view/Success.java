package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Success implements View{

    @FXML
    private Button generateNew, exit;

    private Stage stage;

    public Success(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/SuccessView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        generateNew.setOnAction(event -> {
            Stage stage = (Stage) generateNew.getScene().getWindow();
            GeneratePDF generatePDF = new GeneratePDF(stage);
        });

        generateNew.setOnMouseEntered(event -> {
            generateNew.setStyle("-fx-background-color: darkgrey");
        });

        generateNew.setOnMouseExited(event -> {
            generateNew.setStyle("-fx-background-color: lightgrey");
        });

        exit.setOnAction(event -> {
            Platform.exit();
        });

        exit.setOnMouseEntered(event -> {
            exit.setStyle("-fx-background-color: darkgrey");
        });

        exit.setOnMouseExited(event -> {
            exit.setStyle("-fx-background-color: lightgrey");
        });
    }
}
