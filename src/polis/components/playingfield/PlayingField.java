package polis.components.playingfield;

import polis.components.Manager;
import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.BuildingField;

import java.util.Arrays;

/**
 * Manager van het speelveld. Zorgt voor het (her-)opstarten van het speelveld en
 * faciliteert de handelingen van de onder-managers.
 * **/
public class PlayingField {

    private PlayingFieldView view;
    private BuildingField buildingField;
    private final ActorField actorField;

    public PlayingField(Manager manager) {
        buildingField = new BuildingField(manager);
        actorField = new ActorField(buildingField);
    }

    public void setStartupTiles(boolean isTrees) {
        Arrays.stream(buildingField.getTiles()).forEach(x -> Arrays.fill(x, null));
        actorField.getActors().clear();
        view.getChildren().clear();
        actorField.getSimulator().reset();
        if (isTrees) {
            buildingField.setStartupTrees(-.2f);
        }
        buildingField.setStartupRoads();
    }

    public BuildingField getBuildingField() {
        return buildingField;
    }

    public void setBuildingField(BuildingField buildingField) {
        this.buildingField = buildingField;
    }

    public ActorField getActorField() {
        return actorField;
    }

    public void setView(PlayingFieldView view) {
        this.view = view;
    }

}
