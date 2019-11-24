package view;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import model.Master;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class NewFile implements View {

    @FXML
    private TextField name, address, adminCost, fileName, workingDays, incentiveLeave;
    @FXML
    private ComboBox<String> location, rateType, allowance;
    @FXML
    private Button generate, back;

    private Stage stage;
    private Master master;
    private ObservableList<String> allowanceList, rateTypeList, locationList;

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
    }

    private void setData() {
        Employee emp = new Employee("Stanley", "Sie", "NCR");
        master.setCurrentEmployee(emp);
    }

    private void setupComboBox() {
        locationList = FXCollections.observableArrayList("NCR", "CAR", "REGION I", "REGIION II", "REGION III", "REGION IV-A",
                "REGION IV-B", "REGION V", "REGION VII", "REGION VIII", "REGION IX", "REGION X", "REGION XI",
                "REGION XII", "REGION XIII", "ARMM");
        allowanceList = FXCollections.observableArrayList("Daily", "Monthly");
        rateTypeList = FXCollections.observableArrayList("None");

        location.setItems(locationList);
        rateType.setItems(rateTypeList);
        allowance.setItems(allowanceList);
    }
}
