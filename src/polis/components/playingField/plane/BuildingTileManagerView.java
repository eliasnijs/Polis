package polis.components.playingField.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;

public class BuildingTileManagerView extends Pane implements InvalidationListener {

    private BuildingTileManagerModel model;

    public BuildingTileManagerView(BuildingTileManagerModel buildingTileManagerModel){
        this.model = buildingTileManagerModel;
        this.setTranslateY((double)(-BuildingTileManagerModel.getGridSize())/2 * BuildingTileManagerModel.getCellSize());
        for(BuildingTileView[] row : model.getTiles()){
            for(BuildingTileView cell : row){
                this.getChildren().add(cell);
            }
        }
    }

    // Getters
    public BuildingTileManagerModel getModel(){
        return model;
    }

    // Setters
    public void setModel(BuildingTileManagerModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    // Handles events
    @Override
    public void invalidated(Observable observable) {
        System.out.println("TileManager event to be executed");
    }
}
