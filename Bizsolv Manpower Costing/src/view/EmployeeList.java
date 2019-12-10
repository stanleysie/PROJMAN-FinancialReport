package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;
import model.Master;

import java.util.Optional;

public class EmployeeList implements View {

    @FXML
    private Button back;
    @FXML
    private TableView table;

    private Stage stage;
    private Master master;
    private TableColumn name, status;

    public EmployeeList(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/EmployeeList.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setup();
    }

    public void initialize() {
        back.setOnAction(event ->{
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance back = new Maintenance(stage, master);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #ef5350");
        });
        table.setOnMouseClicked(event -> {
             Employee emp = (Employee) table.getSelectionModel().getSelectedItem();
             if(emp != null) {
                 if(emp.getStatus() == 1) {
                     freezeEmployee(emp);
                 } else {
                     unfreezeEmployee(emp);
                 }
             }
        });
    }

    private void setup() {
        name = new TableColumn("Client Name");
        name.setPrefWidth(300);
        name.setStyle("-fx-alignment: CENTER;");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setResizable(false);
        status = new TableColumn("Client Status");
        status.setPrefWidth(150);
        status.setStyle("-fx-alignment: CENTER;");
        status.setCellValueFactory(new PropertyValueFactory("statusLabel"));
        status.setResizable(false);
        table.getColumns().clear();
        table.getColumns().addAll(name, status);
        table.setItems(master.getAllEmployees());
    }

    private void freezeEmployee(Employee e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Client Status");
        alert.setContentText("Are you sure you want to freeze " + e.getName() + " account?");
        Optional<ButtonType> click = alert.showAndWait();

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setDefaultButton(true);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setDefaultButton(true);

        if(click.get() == ButtonType.OK) {
            e.setStatus(0);
            master.updateEmployee(e);
            reloadTable();
        } else if(click.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    private void unfreezeEmployee(Employee e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Client Status");
        alert.setContentText("Are you sure you want to unfreeze " + e.getName() + " account?");
        Optional<ButtonType> click = alert.showAndWait();

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setDefaultButton(true);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setDefaultButton(true);

        if(click.get() == ButtonType.OK) {
            e.setStatus(1);
            master.updateEmployee(e);
            reloadTable();
        } else if(click.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    private void reloadTable() {
        table.getItems().clear();
        table.setItems(master.getAllEmployees());
    }
}
