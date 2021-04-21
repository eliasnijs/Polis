package polis.components.cursor.cursors;

import polis.components.Manager;
import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorSelect extends Cursor {

    private static final Map<String, String> colors = Map.of(
            "bulldoze", "#D95B6699",
            "select", "#FFFFFF"
    );

    private final Manager manager;

    private final Map<String, Runnable> tools = Map.of(
            "select", this::select,
            "bulldoze", this::bulldoze
    );

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

    public boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() && c[1] >= 0 && c[1] < FieldData.getGridSize());
    }

    // :(
    public void bulldoze() {
        for (int[] c : selected) {
            if (!isAvailable(c)) {
                BuildingTileView v = getBuildingField().getTiles()[c[0]][c[1]];
                BuildingTileModel m = v.getModel();
                if (m.isDestructible()) {
                    getBuildingField().deleteTile(c[0], c[1]);
                    for (int i = 0; i < FieldData.getGridSize(); i += 1) {
                        for (int j = 0; j < FieldData.getGridSize(); j += 1) {
                            if (getBuildingField().getTiles()[i][j] == getBuildingField().getTiles()[c[0]][c[1]]) {
                                if (i != c[0] || j != c[1]) {
                                    getBuildingField().getTiles()[i][j] = null;
                                }
                            }
                        }
                    }
                    getBuildingField().getTiles()[c[0]][c[1]] = null;
                    if (m.getName().equals("road")) {
                        CursorRoads roads = (CursorRoads) manager.getManager(1);
                        boolean[] adjacent = roads.checkNeighbours(c[0], c[1]);
                        ArrayList<int[]> pos = new ArrayList<>();
                        Collections.addAll(pos, new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1});
                        for (int i = 0; i < pos.size(); i++) {
                            int[] s = pos.get(i);
                            if (adjacent[i]) {
                                boolean[] adj = roads.checkNeighbours(c[0] + s[0], c[1] + s[1]);
                                getBuildingField().getTiles()[c[0] + s[0]][c[1] + s[1]].getModel().setNeighbours(adj);
                            }
                        }
                    }
                }
            }
        }
    }

    public void select() {
        for (int[] c : selected) {
            BuildingTileView view = getBuildingField().getTiles()[c[0]][c[1]];
            if (view != null) {
                view.getModel().Update();
            }
        }
    }

}
