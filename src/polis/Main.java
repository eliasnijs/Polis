package polis;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import other.Viewport;
import polis.components.cursor.CursorTileManagerModel;
import polis.components.cursor.CursorTileManagerView;
import polis.components.plane.BuildingTileManagerModel;
import polis.components.plane.BuildingTileManagerView;
import polis.other.ImageLoader;

public class Main extends Application {

    // TODO: Make fxml
    // TODO: Make the UI
    // TODO: Abstract further to combine UI and cursor-mode

    private static final String TITLE = "Polis - A city builder";

    @Override
    public void start(Stage stage) throws Exception {

        CursorTileManagerModel cursorTileManagerModel = new CursorTileManagerModel();
        CursorTileManagerView cursorTileManagerView = new CursorTileManagerView(cursorTileManagerModel);

        ImageLoader imageLoader = new ImageLoader();
        BuildingTileManagerModel buildingTileManagerModel = new BuildingTileManagerModel(imageLoader);
        BuildingTileManagerView buildingTileManagerView = new BuildingTileManagerView(buildingTileManagerModel);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(buildingTileManagerView, cursorTileManagerView);

        Viewport view = new Viewport(gridPane,0.5);

        // Set scene
        Scene scene = new Scene(view, 1600,700);

        // Set stage
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

}
