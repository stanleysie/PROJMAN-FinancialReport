package driver;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Testing;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new Testing(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
