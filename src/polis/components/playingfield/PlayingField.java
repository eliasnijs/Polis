package polis.components.playingfield;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.BuildingField;

import java.util.Arrays;

public class PlayingField {

    private PlayingFieldView view;
    private BuildingField buildingField;
    private final ActorField actorField;

    public PlayingField() {
        buildingField = new BuildingField();
        actorField = new ActorField(buildingField);
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

    public void setStartupTiles(boolean isTrees) {
        Arrays.stream(buildingField.getTiles()).forEach(x -> Arrays.fill(x, null));
        actorField.getActors().clear();
        view.getChildren().clear();
        if (isTrees) {
            buildingField.setStartupTrees(-.2f);
        }
        buildingField.setStartupRoads();
    }

    public void setView(PlayingFieldView view) {
        this.view = view;
    }
}
