package polis;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Viewport;
import polis.components.tile.TileView;
import polis.components.tiles.Grass;

public class Main extends Application {

    private static final String TITLE = "Polis - A city builder";


    @Override
    public void start(Stage stage) throws Exception {
        Grass grass = new Grass();
        TileView t = new TileView(grass);

        Button b = new Button();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(t);
        Viewport view = new Viewport(gridPane,0.5);

        // Set scene
        Scene scene = new Scene(view, 1600,700);

        // Set stage
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
