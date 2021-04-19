package polis.helpers;

import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Collections;

public class RoadChecker {

    private final BuildingField buildingField;
    private final ArrayList<int[]> pos;

    public RoadChecker(BuildingField buildingField) {
        this.buildingField = buildingField;
        this.pos = new ArrayList<>();
        Collections.addAll(pos, new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1});
    }

    public boolean[] check (int row, int column) {
        boolean[] check = new boolean[]{false, false, false, false};
        BuildingTileView[][] tiles = buildingField.getTiles();
        for (int i = 0; i < 4; i++) {
            int[] s = pos.get(i);
            if(bounds(row+s[0],column+s[1])){
                BuildingTileView t = tiles[row+s[0]][column+s[1]];
                if (t != null) {
                    check[i] = t.getModel().getName().equals("road");
                }
            }
        } return check;
    }

    public boolean bounds(int r, int c) {
        return (r >= 0 && r < FieldData.getGridSize() && c >= 0 && c < FieldData.getGridSize());
    }


}
