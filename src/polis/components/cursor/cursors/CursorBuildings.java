package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorBuildings extends Cursor {

    private static final Map<Boolean, String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

    public CursorBuildings(BuildingField buildingField, ArrayList<int[]> selected, CursorField tiles) {
        super(buildingField, selected, tiles);
    }

    @Override
    public void drag(double x, double y) {
    }

    @Override
    public void setStartDrag(double x, double y) {
    }

    @Override
    public void place() {
        placeTiles();
        clearSelectedTiles();
    }

    public boolean checkAvailable() {
        for (int[] c : selected) {
            if (!isAvailable(c)) {
                return false;
            }
        }
        return !selected.isEmpty();
    }

    @Override
    public void colorSelectedTiles() {
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1]);
            getCursorFieldModel().setTile(cursorTile);
            cursorTile.setColor(colors.get(checkAvailable()));
        }
    }

    @Override
    public boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() - 1 && c[1] >= 0 && c[1] < FieldData.getGridSize() - 1);
    }

    @Override
    public void addActiveTile(int[] c) {
        if (checkBounds(c)) {
            Collections.addAll(selected, c, new int[]{c[0], c[1] + 1}, new int[]{c[0] + 1, c[1]}, new int[]{c[0] + 1, c[1] + 1});
        }
    }

    public void placeTiles() {
        if (checkAvailable()) {
            int[] c = selected.get(0);
            if (checkBounds(c)) {
                BuildingTileModel b = new Building(c[0], c[1], getTool());
                getBuildingField().setTile(b);
            }
        }
    }

}
