package polis.components.cursor.cursors;

import polis.components.Manager;
import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

import java.util.ArrayList;
import java.util.Map;


/**
 * Cursor om zaken te selecteren of verwijderen.
 * **/
public class CursorSelect extends Cursor {

    private static final Map<String, String> colors = Map.of(
            "bulldoze", "#D95B6699",
            "select", "#FFFFFF"
    );

    private final Map<String, Runnable> tools = Map.of(
            "select", this::select,
            "bulldoze", this::bulldoze
    );

    private final Manager manager;

    public CursorSelect(BuildingField buildingField, ArrayList<int[]> selected, CursorField tiles, Manager manager) {
        super(buildingField, selected, tiles);
        this.manager = manager;
    }

    @Override
    public void drag(double x, double y) {
    }

    @Override
    public void setStartDrag(double x, double y) {
    }

    @Override
    public void place() {
        tools.get(getTool()).run();
    }

    public void colorSelectedTiles() {
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1]);
            cursorTile.setStroke(colors.get(getTool()));
            getCursorFieldModel().setTile(cursorTile);
        }
    }

    public void addActiveTile(int[] c) {
        if (checkBounds(c)) {
            selected.add(c);
        }
    }

    private void bulldoze() {
        for (int[] c : selected) {
            if (checkAvailability(c)) {
                getBuildingField().deleteTile(c[0], c[1]);
            }
        }
    }

    public boolean checkAvailability(int[] c){
        if (!isAvailable(c)) {
            BuildingTileModel m = getBuildingField().getTiles()[c[0]][c[1]].getModel();
            return m.isDestructible();
        }
        return false;
    }

    private void select() {
        if (selected.size() > 0) {
            int[] c = selected.get(0);
            manager.getStatsConstructor().importBuilding(getBuildingField().getTiles()[c[0]][c[1]]);
            manager.getStatsConstructor().Update();
        } else {
            manager.getStatsConstructor().importBuilding(null);
        }
    }

}
