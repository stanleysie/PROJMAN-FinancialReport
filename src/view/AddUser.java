package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUser implements View{

    @FXML
    private Button back,register;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password,confirmPassword;

    private Stage stage;

    public AddUser(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/AddUserView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: darkgrey");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: lightgrey");
        });

        register.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage);
        });

        register.setOnMouseEntered(event -> {
            register.setStyle("-fx-background-color: darkgrey");
        });

        register.setOnMouseExited(event -> {
            register.setStyle("-fx-background-color: lightgrey");
        });
    }
}
