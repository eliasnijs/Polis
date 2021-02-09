package polis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Hier aanvullen, je wil wellicht niet gewoon maar een HBox tonen?
        Scene scene = new Scene (new HBox());
        stage.setScene(scene);
        stage.setTitle("Polis - 2021 © Universiteit Gent");
        stage.show();
    }

}
