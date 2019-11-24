package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Master;

public class Menu implements View {
    
    @FXML
    private Button generate, adminEdit, logout,load;

    private Stage stage;
    private Master master;

    public Menu(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/MenuView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){

        generate.setOnAction(event -> {
            Stage stage = (Stage) generate.getScene().getWindow();
            NewFile newFile = new NewFile(stage, master);
        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: #535353");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: #ef5350");
        });

        logout.setOnAction(event -> {
            Stage stage = (Stage) logout.getScene().getWindow();
            Login login = new Login(stage);
        });

        logout.setOnMouseEntered(event -> {
            logout.setStyle("-fx-background-color: #535353");
        });

        logout.setOnMouseExited(event -> {
            logout.setStyle("-fx-background-color: #ef5350");
        });

        load.setOnAction(event -> {
//            Stage stage = (Stage) load.getScene().getWindow();
//            NewFile load = new NewFile(stage);
        });

        load.setOnMouseEntered(event -> {
            load.setStyle("-fx-background-color: #535353");
        });

        load.setOnMouseExited(event -> {
            load.setStyle("-fx-background-color: #ef5350");
        });

        adminEdit.setOnAction(event -> {
            Stage stage = (Stage) adminEdit.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage, master);
        });

        adminEdit.setOnMouseEntered(event -> {
            adminEdit.setStyle("-fx-background-color: #535353");
        });

        adminEdit.setOnMouseExited(event -> {
            adminEdit.setStyle("-fx-background-color: #ef5350");
        });

    }
}
