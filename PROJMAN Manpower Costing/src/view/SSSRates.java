package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class SSSRates implements View{

    @FXML
    private Button back, save;

    private Stage stage;

    public SSSRates(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/SSSRatesView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #ef5350");
        });

        save.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("SSS Rates");
            alert.setContentText("Are you sure you want to save the changes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) back.getScene().getWindow();
                Maintenance maintenance = new Maintenance(stage);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        save.setOnMouseEntered(event -> {
            save.setStyle("-fx-background-color: #535353");
        });

        save.setOnMouseExited(event -> {
            save.setStyle("-fx-background-color: #ef5350");
        });
    }
}
