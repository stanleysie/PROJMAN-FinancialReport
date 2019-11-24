package view;

import com.itextpdf.text.DocumentException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Master;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class NewFile implements View {

    @FXML
    private TextField address, adminCost, fileName, workingDays, incentiveLeave;
    @FXML
    private ComboBox<String> name, locations, rateType, allowance;
    @FXML
    private Button generate, back;

    private Stage stage;
    private Master master;
    private ObservableList<String> allowanceList, rateTypeList, locationList, nameList;

    public NewFile(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/NewFile.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setupComboBox();
    }

    public void initialize() {
        generate.setDefaultButton(true);
        generate.setOnAction(event -> {
            boolean done = true;
            if(address.getText().trim().isEmpty()) {
                done = false;
            } else if(workingDays.getText().trim().isEmpty()) {
                done = false;
            } else if(adminCost.getText().trim().isEmpty()) {
                done = false;
            } else if(fileName.getText().trim().isEmpty()) {
                done = false;
            }

            if(done) {
                setData();
                try {
                    GeneratePDF generatePDF = new GeneratePDF(stage, master);
                    generatePDF.generate();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) generate.getScene().getWindow();
                Success success = new Success(stage, master);
            }
        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: #535353");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: #ef5350");
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

        name.valueProperty().addListener((observable, oldValue, newValue) -> {
            int index = name.getSelectionModel().getSelectedIndex();
            
        });
    }

    private void setData() {
        master.setName(name.getSelectionModel().getSelectedItem());
        master.setAddress(address.getText().trim());

    }

    private void setupComboBox() {
        nameList = master.getAllEmployeesName();
        locationList = master.getAllProvincesNames();
        allowanceList = FXCollections.observableArrayList("None");
        rateTypeList = FXCollections.observableArrayList("Daily", "Monthly");

        name.setItems(nameList);
        name.getSelectionModel().select(0);
        locations.setItems(locationList);
        rateType.setItems(rateTypeList);
        allowance.setItems(allowanceList);
    }
}
