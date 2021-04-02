package polis.components.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.Manager;
import polis.components.buildings.buildingtile.BuildingTileView;

public class BuildingTileManagerView extends Pane implements InvalidationListener {

    private final Manager manager;
    private final BuildingTileManagerModel model;

    public BuildingTileManagerView(Manager manager){
        this.manager = manager;
        model = manager.getBuildingField();
        model.addListener(this);
        this.setTranslateX((double)(model.getGridSize()-1)*model.getCellSize());
        manager.setStartupTiles();
    }

    public void addView(BuildingTileView v){
        this.getChildren().add(v);
    }

    public void deleteView(BuildingTileView v){
        this.getChildren().remove(v);
    }

    @Override
    public void invalidated(Observable observable) {
        int mode = model.getMode();
        if (mode == 0) {
            addView(model.getAddView());
        } else {
            deleteView(model.getAddView());
        }
    }

}
