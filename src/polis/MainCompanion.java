package polis;

import javafx.scene.layout.StackPane;
import other.Viewport;
import polis.components.playingfield.cursor.CursorTileManager2;
import polis.components.playingfield.cursor.CursorTileManagerView;
import polis.components.playingfield.plane.BuildingTileManagerView;

public class MainCompanion {

    public StackPane viewportStackPane;

    public void initialize(){


        CursorTileManager2 field = new CursorTileManager2(32,62);

        StackPane stackPane = new StackPane(
                new BuildingTileManagerView(field.getBuildingField()),
                new CursorTileManagerView(field)
        );

        Viewport view = new Viewport(stackPane,0.5);
        viewportStackPane.getChildren().add(view);
    }

}
