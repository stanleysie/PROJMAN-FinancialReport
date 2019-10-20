package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Login implements View{

    @FXML
    private Button loginBtn;

    private Stage stage;

    public Login(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/LoginView.fxml", this);
        stage.setResizable(false);
        stage.setTitle("BizSolv Asia Manpower Costing");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/files/logo.png")));
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        loginBtn.setOnAction(event -> {
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Testing testing = new Testing(stage);
        });

        loginBtn.setOnMouseEntered(event -> {
            loginBtn.setStyle("-fx-background-color: darkgrey");
        });

        loginBtn.setOnMouseExited(event -> {
            loginBtn.setStyle("-fx-background-color: lightgrey");
        });
    }

}
