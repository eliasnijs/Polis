package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String TITLE = "POLIS2021 @ Universiteit Gent";
        String FXML_GAME = "/polis/main.fxml";
        String FXML_START = "/polis/startmenu.fxml";

        Parent startParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(FXML_START)));
        Parent gameParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(FXML_GAME)));

        Scene startScene = new Scene(startParent, 1600,685);
        Scene gameScene = new Scene(gameParent, 1600,685);
        startParent.setOnMouseReleased(e -> stage.setScene(gameScene));

        stage.setScene(startScene);
        stage.setTitle(TITLE);
        stage.show();

    }

}
