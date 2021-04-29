package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.playingfield.buildings.BuildingField;
import polis.helpers.GridCoordsConverter;
import polis.helpers.RoadChecker;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Cursor {

    private final CursorField cursorField;
    private final BuildingField buildingField;
    public ArrayList<int[]> selected;
    private String tool;

    public Cursor(BuildingField bf, ArrayList<int[]> s, CursorField t) {
        this.buildingField = bf;
        this.selected = s;
        this.cursorField = t;
    }

    public BuildingField getBuildingField() {
        return buildingField;
    }

    public CursorField getCursorFieldModel() {
        return cursorField;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        addActiveTile(GridCoordsConverter.coordsToGrid(x, y));
        colorSelectedTiles();
    }

    public boolean isAvailable(int[] c) {
        return buildingField.getTiles()[c[0]][c[1]] == null;
    }

    public void clearSelectedTiles() {
        getCursorFieldModel().deleteTiles();
        selected.clear();
    }

    public void updateSurroundingRoads(int[] c){
        ArrayList<int[]> pos = new ArrayList<>();
        Collections.addAll(pos, new int[]{-1 , 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1});
        boolean[] adjacent = RoadChecker.checkRoadNeighbours(getBuildingField(),c[0],c[1]);
        for (int i = 0; i < pos.size(); i++) {
            int[] s = pos.get(i);
            if (adjacent[i]) {
                boolean[] adj = RoadChecker.checkRoadNeighbours(getBuildingField(), c[0] + s[0], c[1] + s[1]);
                getBuildingField().getTiles()[c[0] + s[0]][c[1] + s[1]].getModel().setNeighbours(adj);
            }
        }
    }

    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void drag(double x, double y);

    public abstract void setStartDrag(double x, double y);

    public abstract void place();

    protected abstract boolean checkBounds(int[] ints);

}