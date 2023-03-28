package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.buildings.BuildingFactory;
import polis.components.playingfield.buildings.tiles.buildings.CommerceFactory;
import polis.components.playingfield.buildings.tiles.buildings.IndustryFactory;
import polis.components.playingfield.buildings.tiles.buildings.ResidenceFactory;
import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Cursor om gebouwen te plaatsen.
 **/
public class CursorBuildings extends Cursor {

    private final Map<String, BuildingFactory> buildings = Map.of(
            "residence", new ResidenceFactory(),
            "industry", new IndustryFactory(),
            "commerce", new CommerceFactory()
    );

    private static final Map<Boolean, String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

    public CursorBuildings(BuildingField buildingField, ArrayList<int[]> selected, CursorField tiles) {
        super(buildingField, selected, tiles);
    }

    @Override
    public void drag(double x, double y) { }

    @Override
    public void setStartDrag(double x, double y) { }

    @Override
    public void place() {
        placeTiles();
        clearSelectedTiles();
    }

    private boolean checkAvailable() {
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
    protected boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() - 1 && c[1] >= 0 && c[1] < FieldData.getGridSize() - 1);
    }

    @Override
    public void addActiveTile(int[] c) {
        if (checkBounds(c)) {
            Collections.addAll(selected, c, new int[]{c[0], c[1] + 1}, new int[]{c[0] + 1, c[1]}, new int[]{c[0] + 1, c[1] + 1});
        }
    }

    private void placeTiles() {
        if (checkAvailable()) {
            int[] c = selected.get(0);
            if (checkBounds(c)) {
                BuildingTileModel b = buildings.get(getTool()).createBuilding();
                b.setPosition(c[0],c[1]);
                getBuildingField().setTile(b);
            }
        }
    }

}
