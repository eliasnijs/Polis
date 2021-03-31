package polis.components.playingfield.plane;

import javafx.scene.layout.Pane;

public class BuildingTileManagerView extends Pane {

    public BuildingTileManagerView(BuildingTileManagerModel buildingTileManagerModel){
        this.setTranslateY((double)(-buildingTileManagerModel.getGridSize())/2 * buildingTileManagerModel.getCellSize());
        for(BuildingTileView[] row : buildingTileManagerModel.getTiles()){
            this.getChildren().addAll(row);
        }
    }

}
