package polis.components.playingfield;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.BuildingField;
import polis.datakeepers.FieldData;

import java.util.Map;

/**
 * Visuele representatie van het veld waar de gebouwen opstaan en de actors in rondzweven.
 * **/
public class PlayingFieldView extends Pane implements InvalidationListener {

    private BuildingField buildingModel;
    private ActorField actorModel;

    private final Map<Integer, Runnable> update = Map.of(
            0, () -> getChildren().add(actorModel.getPending().getView()),
            1, () -> getChildren().remove(actorModel.getPending().getView()),
            2, () -> getChildren().add(buildingModel.getPendingView().getView()),
            3, () -> getChildren().remove(buildingModel.getPendingView().getView())
    );

    public PlayingFieldView(BuildingField buildingModel, ActorField actorField) {
        this.buildingModel = buildingModel;
        this.actorModel = actorField;
        buildingModel.addListener(this);
        actorModel.addListener(this);
        this.setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());
    }

    @Override
    public void invalidated(Observable observable) {
        int t = (observable.equals(buildingModel)) ?
                buildingModel.getPendingView().getMode() + 2 : actorModel.getPending().getMode();
        update.get(t).run();
    }

}
