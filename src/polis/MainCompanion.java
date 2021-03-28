package polis;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import other.Viewport;
import polis.components.cursor.CursorTileManagerModel;
import polis.components.cursor.CursorTileManagerView;
import polis.components.plane.BuildingTileManagerModel;
import polis.components.plane.BuildingTileManagerView;
import polis.other.ImageLoader;

public class MainCompanion {

    public StackPane viewportStackPane;

    public void initialize(){

        ImageLoader imageLoader = new ImageLoader();

        CursorTileManagerModel cursorTileManagerModel = new CursorTileManagerModel();
        CursorTileManagerView cursorTileManagerView = new CursorTileManagerView(cursorTileManagerModel);

        BuildingTileManagerModel buildingTileManagerModel = new BuildingTileManagerModel(imageLoader);
        BuildingTileManagerView buildingTileManagerView = new BuildingTileManagerView(buildingTileManagerModel);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(buildingTileManagerView, cursorTileManagerView);

        Viewport view = new Viewport(stackPane,0.5);
        viewportStackPane.getChildren().add(view);
    }

}
