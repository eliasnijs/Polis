package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.Road;
import polis.helpers.GridCoordsConverter;
import polis.helpers.RoadChecker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Cursor om wegen te plaatsen.
 * **/
public class CursorRoads extends Cursor {

    private static final Map<Boolean, String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

     private int[] startOfDrag;

    public CursorRoads(BuildingField bf, ArrayList<int[]> s, CursorField t) {
        super(bf, s, t);
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
    protected void colorSelectedTiles() {
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1]);
            cursorTile.setColor(colors.get(isAvailable(c)));
            getCursorFieldModel().setTile(cursorTile);
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

    private void selectDragTiles(int x, int y) {
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

    private void placeTiles() {
        for (int[] c : selected) {
            if (isAvailable(c)) {
                boolean[] adjacent = RoadChecker.checkRoadNeighbours(getBuildingField(),c[0],c[1]);
                getBuildingField().setTile(new Road(c[0], c[1], adjacent, true));
                getBuildingField().updateSurroundingRoads(c);
            }
        }
    }

}

























