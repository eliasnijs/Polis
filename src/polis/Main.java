package polis;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    // TODO: MainCompanion
    // TODO: Create a mode manager

    private static final String TITLE = "Polis - A city builder";
    private static final String FXML_LOCATION = "/polis/main.fxml";
    private static final String CSS_LOCATION = "/polis/main.css";


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_LOCATION));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 1600,685);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
