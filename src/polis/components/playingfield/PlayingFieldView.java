package polis.components.playingfield;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.Manager;
import polis.components.playingfield.buildings.BuildingFieldModel;
import polis.components.playingfield.buildings.BuildingTileView;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingBuildingTileView;

public class PlayingFieldView extends Pane implements InvalidationListener {

    private final BuildingFieldModel model;

    public PlayingFieldView(Manager manager) {
        this.model = manager.getBuildingField();
        model.addListener(this);
        this.setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());
    }

    public void Update(){
        PendingBuildingTileView pending = model.getPendingView();
        if (pending.getMode() == 0) {
            addView(pending.getView());
        } else {
            deleteView(pending.getView());
        }
    }

    public void addView(BuildingTileView v) {
        this.getChildren().add(v);
    }

    public void deleteView(BuildingTileView v) {
        this.getChildren().remove(v);
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
