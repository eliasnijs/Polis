package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.components.playingfield.buildings.tiles.Road;
import polis.datakeepers.FieldData;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorRoads extends Cursor {

    private static final Map<Boolean, String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

    private final ArrayList<int[]> pos;
    private int[] startOfDrag;

    public CursorRoads(BuildingField bf, ArrayList<int[]> s, CursorField t) {
        super(bf, s, t);
        pos = new ArrayList<>();
        Collections.addAll(pos, new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1});
    }

    @Override
    public boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() && c[1] >= 0 && c[1] < FieldData.getGridSize());
    }

    @Override
    protected void addActiveTile(int[] c) {
        if (checkBounds(c)) {
            selected.add(c);
        }
    }

    @Override
    public void setStartDrag(double x, double y) {
        startOfDrag = GridCoordsConverter.coordsToGrid(x, y);
    }

    @Override
    public void place() {
        placeTiles();
        clearSelectedTiles();
    }

    @Override
    public void colorSelectedTiles() {
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1]);
            cursorTile.setColor(colors.get(isAvailable(c)));
            getCursorFieldModel().setTile(cursorTile);
        }
    }

    public void selectDragTiles(int x, int y) {
        int t = (startOfDrag[1] > y) ? -1 : 1;
        int i = startOfDrag[1];
        while (i != y + t) {
            addActiveTile(new int[]{x, i});
            i += t;
        }
        t = (startOfDrag[0] > x) ? -1 : 1;
        i = startOfDrag[0];
        while (i != x) {
            addActiveTile(new int[]{i, startOfDrag[1]});
            i += t;
        }
    }

    @Override
    public void drag(double x, double y) {
        int[] c = GridCoordsConverter.coordsToGrid(x, y);
        if (startOfDrag != null) {
            clearSelectedTiles();
            selectDragTiles(c[0], c[1]);
            colorSelectedTiles();
        }
    }

    public void placeTiles() {
        for (int[] c : selected) {
            if (isAvailable(c)) {
                boolean[] adjacent = checkNeighbours(c[0], c[1]);
                Road r = new Road(c[0], c[1], adjacent, true);
                getBuildingField().setTile(r);
                for (int i = 0; i < pos.size(); i++) {
                    int[] s = pos.get(i);
                    if (adjacent[i]) {
                        boolean[] adj = checkNeighbours(c[0] + s[0], c[1] + s[1]);
                        getBuildingField().getTiles()[c[0] + s[0]][c[1] + s[1]].getModel().setNeighbours(adj);
                    }
                }
            }
        }
    }

    public boolean[] checkNeighbours(int row, int column) {
        boolean[] neighbours = new boolean[]{false, false, false, false};
        for (int i = 0; i < 4; i++) {
            int[] c = pos.get(i);
            if (checkBounds(new int[]{row + c[0], column + c[1]})) {
                BuildingTileView t = getBuildingField().getTiles()[row + c[0]][column + c[1]];
                if (t != null) {
                    if (t.getModel().getName().equals("road")) {
                        neighbours[i] = true;
                    }
                }
            }
        }
        return neighbours;
    }

}
























