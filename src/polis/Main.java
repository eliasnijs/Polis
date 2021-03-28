package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // TODO: MainCompanion
    // TODO: Create a mode manager

    private static final String TITLE = "Polis - A city builder";
    private static final String FXML_LOCATION = "/polis/main.fxml";
    private static final String CSS_LOCATION = "/polis/main.css";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_LOCATION));
        root.getStylesheets().addAll(CSS_LOCATION);
        Scene scene = new Scene(root, 1600,685);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
