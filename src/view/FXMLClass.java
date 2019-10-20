package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class FXMLClass {

    private static Scene scene;

    public static Scene getScene(String location, View view) {
        FXMLLoader loader = new FXMLLoader(FXMLClass.class.getResource(location));
        loader.setController(view);
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

}
