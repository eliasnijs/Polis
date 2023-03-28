package polis;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

/**
 *  Start het programma.
 * **/

public class Main extends Application {

   @Override
	public void start(Stage stage) throws Exception {
		Font.loadFont(Objects.requireNonNull(Main.class.getResource("/polis/fonts/VT323-Regular.ttf")).toExternalForm(), 10);

		Parent startParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/polis/startmenu.fxml")));
      Scene startScene = new Scene(startParent, 1600, 685);

      Parent gameParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/polis/main.fxml")));
      Scene gameScene = new Scene(gameParent, startScene.getWidth(), startScene.getHeight());

      startParent.setOnMouseReleased(e -> stage.setScene(gameScene));
      startParent.setOnKeyReleased(e -> stage.setScene(gameScene));

      stage.setScene(startScene);
      stage.setTitle("POLIS2021 @ Universiteit Gent");
      stage.show();
   }

}
