package view;

import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;
import model.Employee;
import model.Master;

public class AddAccount implements View {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password, confirmpassword;
    @FXML
    private Button back,register;

    private Stage stage;
    private Master master;

    public AddAccount(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/AddAccountView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setup();
    }

    public void initialize() {
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
            if(username.getText().trim().isEmpty()) {
                Toast.makeText(stage, "Username is empty", 2000, 1000, 1000, -225, 0, javafx.scene.paint.Color.RED);
                done = false;
            } else if(password.getText().trim().isEmpty() || confirmpassword.getText().trim().isEmpty()) {
                Toast.makeText(stage, "Password is empty", 2000, 1000, 1000, -225, 0, javafx.scene.paint.Color.RED);
                done = false;
            }
            if(done) {
                Account a = new Account();
                a.setUsername(username.getText().trim());
                a.setPassword(password.getText().trim());
                a.setAccounttype("staff");
                master.addAccount(a);
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

    }
}
