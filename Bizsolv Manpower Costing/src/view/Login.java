package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Master;

public class Login implements View{

    @FXML
    private Button loginBtn;
    @FXML
    private TextField username, password;

    private Stage stage;
    private Master master;

    public Login(Stage stage) {
        this.stage = stage;
        this.master = new Master();
        Scene scene = FXMLClass.getScene("/view/LoginView.fxml", this);
        stage.setResizable(false);
        stage.setTitle("BizSolv Asia Manpower Costing");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/files/logo.png")));
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        loginBtn.setDefaultButton(true);
        loginBtn.setOnAction(event -> {
            boolean done = true;
            if(username.getText().trim().isEmpty()) {
                Toast.makeText(this.stage, "Please input a valid username", 2000, 1000, 1000, 15, 0);
                done = false;
            } else if(password.getText().trim().isEmpty()) {
                Toast.makeText(this.stage, "Please input a valid password", 2000, 1000, 1000, 15, 0);
                done = false;
            }
            if(done) {
                if(loginValidation()) {
                    Stage stage = (Stage) loginBtn.getScene().getWindow();
                    Menu menu = new Menu(stage, master);
                }
            }
        });

        loginBtn.setOnMouseEntered(event -> {
            loginBtn.setStyle("-fx-background-color: #535353");
        });

        loginBtn.setOnMouseExited(event -> {
            loginBtn.setStyle("-fx-background-color: #ef5350");
        });
    }

    public boolean loginValidation() {
        if(username.getText().trim().equalsIgnoreCase("admin")) {
            if(password.getText().equalsIgnoreCase("admin")) {
                return true;
            } else {
                Toast.makeText(this.stage, "Invalid password", 2000, 1000, 1000, 65, 0);
                return false;
            }
        } else {
            Toast.makeText(this.stage, "Invalid username", 2000, 1000, 1000, 65, 0);
            return false;
        }
    }
}
