package driver;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Login;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new Login(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
