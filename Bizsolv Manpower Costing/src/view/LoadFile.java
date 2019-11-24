package view;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Master;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

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
        Scene scene = FXMLClass.getScene("/view/LoadFile.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();

        setData();
    }

    public void initialize() {
        saveChanges.setDefaultButton(true);
        saveChanges.setOnAction(event -> {

        });

        saveChanges.setOnMouseEntered(event -> {
            saveChanges.setStyle("-fx-background-color: #535353");
        });

        saveChanges.setOnMouseExited(event -> {
            saveChanges.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{
            Stage stage = (Stage) back.getScene().getWindow();
            Menu menu = new Menu(stage, master);
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
