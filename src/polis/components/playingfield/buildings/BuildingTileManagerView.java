package polis.components.playingfield.buildings;

import javafx.scene.layout.Pane;
import polis.components.playingfield.buildings.buildingtile.BuildingTileView;

public class BuildingTileManagerView extends Pane {

    public BuildingTileManagerView(BuildingTileManagerModel buildingTileManagerModel){
        this.setTranslateY((double)(-buildingTileManagerModel.getGridSize())/2 * buildingTileManagerModel.getCellSize());
        for(BuildingTileView[] row : buildingTileManagerModel.getTiles()){
            this.getChildren().addAll(row);
        }
    }

}
