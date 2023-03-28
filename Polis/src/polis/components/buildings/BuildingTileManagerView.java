package polis.components.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.Manager;
import polis.components.buildings.buildingtile.BuildingTileView;

public class BuildingTileManagerView extends Pane implements InvalidationListener {

    private final BuildingTileManagerModel model;

    public BuildingTileManagerView(Manager manager) {
        this.model = manager.getBuildingField();
        model.addListener(this);
        this.setTranslateX((double) (model.getGridSize() - 1) * model.getCellSize());
        manager.setStartupTiles();
    }

    public void addView(BuildingTileView v) {
        this.getChildren().add(v);
    }

    public void deleteView(BuildingTileView v) {
        this.getChildren().remove(v);
    }

    @Override
    public void invalidated(Observable observable) {
        BuildingTileView t = model.getPendingView();
        if (model.getPendingMode() == 0) {
            addView(t);
        } else {
            deleteView(t);
        }
    }

}
