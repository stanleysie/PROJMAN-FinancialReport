package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Employee;
import model.Master;

public class AddEmployee implements View{

    @FXML
    private Button back,register;
    @FXML
    private TextField firstname, lastname;
    @FXML
    private TextArea address;
    @FXML
    private ComboBox<String> locations;

    private Stage stage;
    private Master master;

    public AddEmployee(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/AddEmployeeView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setup();
    }

    public void initialize(){
        register.setDefaultButton(true);
        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage, master);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #ef5350");
        });

        register.setOnAction(event -> {
            boolean done = true;
            if(firstname.getText().trim().isEmpty()) {
                Toast.makeText(stage, "First Name is empty", 2000, 1000, 1000, -225, 0, Color.RED);
                done = false;
            } else if(lastname.getText().trim().isEmpty()) {
                Toast.makeText(stage, "Last Name is empty", 2000, 1000, 1000, -225, 0, Color.RED);
                done = false;
            } else if(address.getText().trim().isEmpty()) {
                Toast.makeText(stage, "Address is empty", 2000, 1000, 1000, -225, 0, Color.RED);
                done = false;
            }
            if(done) {
                Employee e = new Employee(firstname.getText().trim(), lastname.getText().trim(), locations.getSelectionModel().getSelectedItem(), address.getText().trim());
                master.addEmployee(e);
                Stage stage = (Stage) back.getScene().getWindow();
                Maintenance maintenance = new Maintenance(stage, master);
            }
        });

        register.setOnMouseEntered(event -> {
            register.setStyle("-fx-background-color: #535353");
        });

        register.setOnMouseExited(event -> {
            register.setStyle("-fx-background-color: #ef5350");
        });
    }

    private void setup() {
        locations.setItems(master.getAllProvincesNames());
        locations.getSelectionModel().select(0);
    }
}
