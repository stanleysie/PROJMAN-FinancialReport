package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DailyReport;
import model.Employee;
import model.Master;
import model.MonthlyReport;

public class LoadSelect implements View {

    @FXML
    private Button load, menu, back;
    @FXML
    private Label employee;
    @FXML
    private TableView table;

    private Stage stage;
    private Master master;
    private TableColumn tableColumn;
    private ObservableList<Employee> employees;

    public LoadSelect(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/LoadSelectView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        this.employees = FXCollections.observableArrayList();
        setup();
    }

    public void initialize() {
        load.setDefaultButton(true);
        load.setOnAction(event -> {
            Stage stage = (Stage) load.getScene().getWindow();
            LoadFile loadFile = new LoadFile(stage, master);
        });

        load.setOnMouseEntered(event -> {
            load.setStyle("-fx-background-color: #535353");
        });

        load.setOnMouseExited(event -> {
            load.setStyle("-fx-background-color: #ef5350");
        });

        menu.setOnAction(event ->{
            Stage stage = (Stage) menu.getScene().getWindow();
            Menu menu = new Menu(stage, master);
        });

        menu.setOnMouseEntered(event -> {
            menu.setStyle("-fx-background-color: #535353");
        });

        menu.setOnMouseExited(event -> {
            menu.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{
            setup();
        });

        back.setOnMouseEntered(event -> {
            System.out.println("Testing in");
            back.setStyle("-fx-border-color: #535353; -fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            System.out.println("Testin out");
            back.setStyle("-fx-background-color: #FFFFFF");
        });

        table.setOnMouseClicked(event -> {
            if(!employee.isVisible()) {
                String name = ((Employee) table.getSelectionModel().getSelectedItem()).getName();
                master.setCurrentEmployee((Employee) table.getSelectionModel().getSelectedItem());
                setVersions(name);
            } else {
                if(table.getSelectionModel().getSelectedItem() != null) {
                    if(table.getSelectionModel().getSelectedItem() instanceof DailyReport) {
                        master.getReport(((DailyReport) table.getSelectionModel().getSelectedItem()).getVersion());
                    } else if(table.getSelectionModel().getSelectedItem() instanceof MonthlyReport) {
                        master.getReport(((MonthlyReport) table.getSelectionModel().getSelectedItem()).getVersion());
                    }
                }
            }
        });
    }

    public void setup() {
        back.setVisible(false);
        employee.setVisible(false);
        tableColumn = new TableColumn("Clients");
        tableColumn.setCellValueFactory(new PropertyValueFactory("name"));
        tableColumn.setPrefWidth(450);
        tableColumn.setStyle("-fx-alignment: CENTER;");
        tableColumn.setResizable(false);
        table.getColumns().clear();
        table.getColumns().add(tableColumn);
        table.setItems(master.getAllEmployees());
    }

    public void setVersions(String name) {
        employee.setVisible(true);
        back.setVisible(true);
        employee.setText(name);
        tableColumn = new TableColumn("Document Versions");
        tableColumn.setPrefWidth(450);
        tableColumn.setStyle("-fx-alignment: CENTER;");
        tableColumn.setCellValueFactory(new PropertyValueFactory("version"));
        tableColumn.setResizable(false);
        table.getColumns().clear();
        table.getColumns().add(tableColumn);
        table.setItems(master.getEmployeesVersion(name));
    }
}
