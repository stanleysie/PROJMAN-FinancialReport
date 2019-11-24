package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public final class Toast {

    public static void makeText(Stage owner, String message, int delay, int fadeIn, int fadeOut, int addWidth, int addHeight, Color color) {
        Stage toast = new Stage();
        toast.initOwner(owner);
        toast.initStyle(StageStyle.TRANSPARENT);
        toast.setX(owner.getWidth() + addWidth);
        toast.setY(owner.getHeight() + addHeight);

        Text text = new Text(message);
        text.setFont(Font.font("Verdana", 15));
        text.setFill(color);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0.2, 0.2, 0.2, 0.2); -fx-padding: 20px;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toast.setScene(scene);
        toast.show();

        Timeline fadeInTime = new Timeline();
        KeyFrame fadeInKey = new KeyFrame(Duration.millis(fadeIn), new KeyValue(toast.getScene().getRoot().opacityProperty(), 1));
        fadeInTime.getKeyFrames().add(fadeInKey);
        fadeInTime.setOnFinished((value) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                Timeline fadeOutTime = new Timeline();
                KeyFrame fadeOutKey = new KeyFrame(Duration.millis(fadeOut), new KeyValue(toast.getScene().getRoot().opacityProperty(), 0));
                fadeOutTime.getKeyFrames().add(fadeOutKey);
                fadeOutTime.setOnFinished((val) -> {
                    toast.close();
                });
                fadeOutTime.play();
            }).start();
        });
        fadeInTime.play();
    }
}
