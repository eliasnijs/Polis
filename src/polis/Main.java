package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE = "Polis - A city builder";
    private static final String FXML_GAME = "/polis/main.fxml";
    private static final String FXML_START = "/polis/startmenu.fxml";


    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader start = new FXMLLoader(getClass().getResource(FXML_START));
        Parent startParent = start.load();

        FXMLLoader game = new FXMLLoader(getClass().getResource(FXML_GAME));
        Parent gameParent = game.load();

        Scene gameScene = new Scene(gameParent, 1600,685);

        startParent.setOnMouseReleased(e -> {stage.setScene(gameScene);
            System.out.println("asd");});
        Scene startScene = new Scene(startParent, 1600,685);


        stage.setScene(startScene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
