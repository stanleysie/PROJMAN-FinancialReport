package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Master;

import java.util.Optional;

public class LoadFile implements View {

    @FXML
    private TextField name, address, adminCost, fileName, workingDays, incentiveLeave;
    @FXML
    private ComboBox<String> locations, rateType, allowance;
    @FXML
    private Button saveChanges, back;

    private Stage stage;
    private Master master;

    public LoadFile(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/LoadFile.fxml", this)   ;
        this.stage.setScene(scene);
        this.stage.show();

        setData();
    }

    public void initialize() {
        saveChanges.setDefaultButton(true);
        saveChanges.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Load File");
            alert.setContentText("Are you sure you want to save the changes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) saveChanges.getScene().getWindow();
                Menu menu = new Menu(stage, master);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        saveChanges.setOnMouseEntered(event -> {
            saveChanges.setStyle("-fx-background-color: #535353");
        });

        saveChanges.setOnMouseExited(event -> {
            saveChanges.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{
            Stage stage = (Stage) back.getScene().getWindow();
            LoadSelect loadSelect = new LoadSelect(stage, master);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #ef5350");
        });
    }

    private void setData() {

    }
}
