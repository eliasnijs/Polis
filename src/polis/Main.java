package polis;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Viewport;
import polis.components.cursor.CursorPlainModel;
import polis.components.cursor.CursorPlainView;
import polis.components.plane.TileManagerModel;
import polis.components.plane.TileManagerView;
import polis.other.ImageLoader;

public class Main extends Application {

    // TODO: Clean up the cursor package
    // TODO: Make the UI
    // TODO: Abstract further to combine UI and cursor-mode

    private static final String TITLE = "Polis - A city builder";

    @Override
    public void start(Stage stage) throws Exception {

        CursorPlainModel cursorPlainModel = new CursorPlainModel();
        CursorPlainView cursorPlainView = new CursorPlainView(cursorPlainModel);
        cursorPlainView.setTranslateY(-16*64);

        ImageLoader imageLoader = new ImageLoader();
        TileManagerModel tileManagerModel = new TileManagerModel(imageLoader);
        TileManagerView tileManagerView = new TileManagerView(tileManagerModel);
        tileManagerView.setTranslateY(-16*64);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(tileManagerView,cursorPlainView);

        Viewport view = new Viewport(gridPane,0.5);

        // Set scene
        Scene scene = new Scene(view, 1600,700);

        // Set stage
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
