package polis.components.playingfield;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.BuildingField;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingActorView;
import polis.datatransferers.PendingBuildingTileView;

public class PlayingFieldView extends Pane implements InvalidationListener {

    private final BuildingField buildingModel;
    private final ActorField actorModel;

    public PlayingFieldView(BuildingField buildingModel, ActorField actorField) {
        this.buildingModel = buildingModel;
        this.actorModel = actorField;
        buildingModel.addListener(this);
        actorModel.addListener(this);
        this.setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());
    }

    public void updateBuildings() {
        PendingBuildingTileView pending = buildingModel.getPendingView();
        if (pending.getMode() == 0) {
            getChildren().add(pending.getView());
        } else {
            getChildren().remove(pending.getView());
        }
    }

    public void updateActors() {
        PendingActorView pending = actorModel.getPending();
        System.out.println(pending.getMode() + " " + pending.getView().getActor());
        if (pending.getMode() == 0) {
            getChildren().add(pending.getView());
        } else {
            getChildren().remove(pending.getView());
        }
    }

    @Override
    public void invalidated(Observable observable) {
        if (observable.equals(buildingModel)) {
            updateBuildings();
        } else {
            updateActors();
        }
    }

}
