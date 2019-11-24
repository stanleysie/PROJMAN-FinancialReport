package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Master;

public class AddUser implements View{

    @FXML
    private Button back,register;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password,confirmPassword;

    private Stage stage;
    private Master master;

    public AddUser(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/AddUserView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
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
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage, master);
        });

        register.setOnMouseEntered(event -> {
            register.setStyle("-fx-background-color: #535353");
        });

        register.setOnMouseExited(event -> {
            register.setStyle("-fx-background-color: #ef5350");
        });
    }
}
