package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Maintenance implements View{

    @FXML
    private Button sss, pagibig, provincial, back, addUser;

    private Stage stage;

    public Maintenance(Stage stage) {
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/MaintenanceView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize(){
        back.setOnAction(event ->{
            Stage stage = (Stage) sss.getScene().getWindow();
            Testing testing = new Testing(stage);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: darkgrey");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: lightgrey");
        });
        
        addUser.setOnAction(event -> {
            Stage stage = (Stage) sss.getScene().getWindow();
            AddUser addUser = new AddUser(stage);
        });

        addUser.setOnMouseEntered(event -> {
            addUser.setStyle("-fx-background-color: darkgrey");
        });

        addUser.setOnMouseExited(event -> {
            addUser.setStyle("-fx-background-color: lightgrey");
        });

        sss.setOnAction(event -> {
            Stage stage = (Stage) sss.getScene().getWindow();
            SSSRates sssRates = new SSSRates(stage);
        });

        sss.setOnMouseEntered(event -> {
            sss.setStyle("-fx-background-color: darkgrey");
        });

        sss.setOnMouseExited(event -> {
            sss.setStyle("-fx-background-color: lightgrey");
        });

        pagibig.setOnAction(event -> {
            Stage stage = (Stage) pagibig.getScene().getWindow();
            SSSRates sssRates = new SSSRates(stage);
        });

        pagibig.setOnMouseEntered(event -> {
            pagibig.setStyle("-fx-background-color: darkgrey");
        });

        pagibig.setOnMouseExited(event -> {
            pagibig.setStyle("-fx-background-color: lightgrey");
        });

        provincial.setOnAction(event -> {
            Stage stage = (Stage) provincial.getScene().getWindow();
            SSSRates sssRates = new SSSRates(stage);
        });

        provincial.setOnMouseEntered(event -> {
            provincial.setStyle("-fx-background-color: darkgrey");
        });

        provincial.setOnMouseExited(event -> {
            provincial.setStyle("-fx-background-color: lightgrey");
        });
    }
}
