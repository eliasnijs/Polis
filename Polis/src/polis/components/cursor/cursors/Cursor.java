package polis.components.cursor.cursors;

import polis.components.cursor.CursorField;
import polis.components.playingfield.buildings.BuildingField;
import polis.datakeepers.FieldData;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;


/**
 * Cursor klasse met algemene methodes.
 **/
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

    protected boolean checkBounds(int[] c) {
        return (c[0] >= 0 && c[0] < FieldData.getGridSize() && c[1] >= 0 && c[1] < FieldData.getGridSize());
    }

    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void drag(double x, double y);

    public abstract void setStartDrag(double x, double y);

    public abstract void place();

}