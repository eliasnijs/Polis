package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import polis.other.MusicPlayer;

import java.io.File;

public class Main extends Application {

    private static final String TITLE = "POLIS2021 @ Universiteit Gent";
    private static final String FXML_GAME = "/polis/main.fxml";
    private static final String FXML_START = "/polis/startmenu.fxml";

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent startParent = FXMLLoader.load(getClass().getResource(FXML_START));
        Parent gameParent = FXMLLoader.load(getClass().getResource(FXML_GAME));

        Scene startScene = new Scene(startParent, 1600,685);
        Scene gameScene = new Scene(gameParent, 1600,685);
        startParent.setOnMouseReleased(e -> stage.setScene(gameScene));

        stage.setScene(startScene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
