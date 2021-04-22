package polis.components.playingfield.actors;

import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.datakeepers.FieldData;

public class MoverManager {

    private final static int[] DC = {-1, 0, 1, 0};
    private final static int[] DR = {0, -1, 0, 1};
    private final BuildingField buildingField;

    public MoverManager(ActorField actorField) {
        this.buildingField = actorField.getBuildingField();
    }

    public boolean isRoad(int row, int column) {
        if (checkBounds(new int[]{row, column})) {
            BuildingTileView t = buildingField.getTiles()[row][column];
            if (t != null) {
                return t.getModel().getName().equals("road");
            }
        }
        return false;
    }

    public BuildingTileModel getBuilding(int row, int column, int direction) {
        if (checkBounds(new int[]{row + DR[direction], column + DC[direction]})) {
            BuildingTileView t = buildingField.getTiles()[row + DR[direction]][column + DC[direction]];
            if (t != null) {
                return t.getModel();
            }
        }
        return null;
    }

    public boolean canMove(int row, int column, int direction) {
        return isRoad(row + DR[direction], column + DC[direction]);
    }

    public boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() && c[1] >= 0 && c[1] < FieldData.getGridSize());
    }

}
