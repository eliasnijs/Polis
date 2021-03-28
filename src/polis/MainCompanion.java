package polis;

import javafx.scene.layout.StackPane;
import other.Viewport;
import polis.components.playingField.cursor.CursorTileManagerModel;
import polis.components.playingField.cursor.CursorTileManagerView;
import polis.components.playingField.plane.BuildingTileManagerModel;
import polis.components.playingField.plane.BuildingTileManagerView;
import polis.other.ImageLoader;

public class MainCompanion {

    public StackPane viewportStackPane;

    public void initialize(){
        ImageLoader imageLoader = new ImageLoader();

        StackPane stackPane = new StackPane(
                new BuildingTileManagerView(new BuildingTileManagerModel(imageLoader)),
                new CursorTileManagerView(new CursorTileManagerModel())
        );

        Viewport view = new Viewport(stackPane,0.5);
        viewportStackPane.getChildren().add(view);
    }

}
