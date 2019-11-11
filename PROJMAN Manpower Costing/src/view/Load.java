package view;

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Load implements View{

    @FXML
    private TextField name, address;
    @FXML
    private Button generate, logout;

    private Stage stage;

    public Load(Stage stage) {
        System.out.println("SCENE: TESTING");
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/LoadView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void initialize() {
//        generate.setDefaultButton(true);
//        generate.setOnAction(event -> {
//            System.out.println("PRINTING");
//            try {
//                generate();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Stage stage = (Stage) generate.getScene().getWindow();
//            Success success = new Success(stage);
//        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: #535353");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: #ef5350");
        });

        logout.setOnAction(event ->{
            Stage stage = (Stage) logout.getScene().getWindow();
            Menu menu = new Menu(stage);
        });

        logout.setOnMouseEntered(event -> {
            logout.setStyle("-fx-background-color: #535353");
        });

        logout.setOnMouseExited(event -> {
            logout.setStyle("-fx-background-color: #ef5350");
        });
    }
}
