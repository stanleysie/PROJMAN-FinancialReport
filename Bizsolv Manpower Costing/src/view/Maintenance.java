package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Master;

public class Maintenance implements View{

    @FXML
    private Button back, addEmployee, listEmployee, logout;

    private Stage stage;
    private Master master;

    public Maintenance(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/MaintenanceView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
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
        
        addEmployee.setOnAction(event -> {
            Stage stage = (Stage) addEmployee.getScene().getWindow();
            AddEmployee addEmployee = new AddEmployee(stage, master);
        });

        addEmployee.setOnMouseEntered(event -> {
            addEmployee.setStyle("-fx-background-color: #ef5350");
        });

        addEmployee.setOnMouseExited(event -> {
            addEmployee.setStyle("-fx-background-color: #ffffff");
            addEmployee.setStyle("-fx-border-color: #ef5350");
        });

        listEmployee.setOnAction(event -> {
            Stage stage = (Stage) addEmployee.getScene().getWindow();
            EmployeeList list = new EmployeeList(stage, master);
        });

        listEmployee.setOnMouseEntered(event -> {
            listEmployee.setStyle("-fx-background-color: #ef5350");
        });

        listEmployee.setOnMouseExited(event -> {
            listEmployee.setStyle("-fx-background-color: #ffffff");
            listEmployee.setStyle("-fx-border-color: #ef5350");
        });

        logout.setOnAction(event ->{
            Stage stage = (Stage) logout.getScene().getWindow();
            Login login = new Login(stage);
        });

        logout.setOnMouseEntered(event -> {
            logout.setStyle("-fx-background-color: #535353");
        });

        logout.setOnMouseExited(event -> {
            logout.setStyle("-fx-background-color: #ef5350");
        });
    }
}
