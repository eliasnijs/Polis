package polis.components.playingfield.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.components.playingfield.buildings.buildingtile.BuildingTileView;

public class BuildingTileManagerView extends Pane implements InvalidationListener {

    private final BuildingTileManagerModel model;

    public BuildingTileManagerView(BuildingTileManagerModel buildingTileManagerModel){
        model = buildingTileManagerModel;
        model.addListener(this);
        this.setTranslateX((double)(model.getGridSize()-1)*model.getCellSize());
    }

    public void addView(BuildingTileView v){
        this.getChildren().add(v);
    }

    @Override
    public void invalidated(Observable observable) {
        addView(model.getAddView());
    }

}
