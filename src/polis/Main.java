package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE = "Polis - A city builder";
    private static final String FXML_LOCATION = "/polis/main.fxml";

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
