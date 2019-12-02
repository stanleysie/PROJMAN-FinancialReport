package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Master;

public class Maintenance implements View{

    @FXML
    private Button provincial, back, addUser, logout;

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
        
        addUser.setOnAction(event -> {
            Stage stage = (Stage) addUser.getScene().getWindow();
            AddUser addUser = new AddUser(stage, master);
        });

        addUser.setOnMouseEntered(event -> {
            addUser.setStyle("-fx-background-color: #ef5350");
        });

        addUser.setOnMouseExited(event -> {
            addUser.setStyle("-fx-background-color: #ffffff");
            addUser.setStyle("-fx-border-color: #ef5350");
        });

        provincial.setOnAction(event -> {
            Stage stage = (Stage) this.provincial.getScene().getWindow();
            Provincial provincial = new Provincial(stage, master);
        });

        provincial.setOnMouseEntered(event -> {
            provincial.setStyle("-fx-background-color: #ef5350");
        });

        provincial.setOnMouseExited(event -> {
            provincial.setStyle("-fx-background-color: #ffffff");
            provincial.setStyle("-fx-border-color: #ef5350");
        });

        logout.setOnAction(event ->{
            Stage stage = (Stage) logout.getScene().getWindow();
            Login login = new Login(stage);
        });

        logout.setOnMouseEntered(event -> {
            logout.setStyle("-fx-logoutground-color: #535353");
        });

        logout.setOnMouseExited(event -> {
            logout.setStyle("-fx-logoutground-color: #ef5350");
        });
    }
}
