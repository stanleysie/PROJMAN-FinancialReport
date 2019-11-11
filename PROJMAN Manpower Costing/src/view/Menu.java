package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu implements View {
    
    @FXML
    private Button generate, adminEdit, logout,load;

    private Stage stage;

    public Menu(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/MenuView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){

        generate.setOnAction(event -> {
            Stage stage = (Stage) generate.getScene().getWindow();
            Testing testing = new Testing(stage);
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
            Stage stage = (Stage) load.getScene().getWindow();
            Load load = new Load(stage);
        });

        load.setOnMouseEntered(event -> {
            load.setStyle("-fx-background-color: #535353");
        });

        load.setOnMouseExited(event -> {
            load.setStyle("-fx-background-color: #ef5350");
        });

        adminEdit.setOnAction(event -> {
            Stage stage = (Stage) adminEdit.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage);
        });

        adminEdit.setOnMouseEntered(event -> {
            adminEdit.setStyle("-fx-background-color: #535353");
        });

        adminEdit.setOnMouseExited(event -> {
            adminEdit.setStyle("-fx-background-color: #ef5350");
        });

    }
}
