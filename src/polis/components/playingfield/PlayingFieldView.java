package polis.components.playingfield;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.Manager;
import polis.components.playingfield.buildings.BuildingField;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingBuildingTileView;

public class PlayingFieldView extends Pane implements InvalidationListener {

    private final BuildingField model;

    public PlayingFieldView(Manager manager) {
        this.model = manager.getBuildingField();
        model.addListener(this);
        this.setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());
    }

    public void Update() {
        PendingBuildingTileView pending = model.getPendingView();
        if (pending.getMode() == 0) {
            getChildren().add(pending.getView());
        } else {
            getChildren().remove(pending.getView());
        }
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
