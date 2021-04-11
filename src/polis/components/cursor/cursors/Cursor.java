package polis.components.cursor.cursors;

import polis.components.playingfield.buildings.BuildingFieldModel;
import polis.components.cursor.CursorFieldModel;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;

public abstract class Cursor {

    private String tool;

    private final CursorFieldModel cursorField;
    private final BuildingFieldModel buildingField;
    public ArrayList<int[]> selected;

    public Cursor(BuildingFieldModel bf, ArrayList<int[]> s, CursorFieldModel t){
        this.buildingField = bf;
        this.selected = s;
        this.cursorField = t;
    }

    public BuildingFieldModel getBuildingField() {
        return buildingField;
    }

    public CursorFieldModel getCursorFieldModel(){
        return cursorField;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int[] getTileFromCoordinates(double x, double y){
        return GridCoordsConverter.coordsToGrid(x, y);
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        addActiveTile(getTileFromCoordinates(x,y));
        colorSelectedTiles();
    }

    public boolean isAvailable(int[] c) {
        return buildingField.getTiles()[c[0]][c[1]] == null;
    }

    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void clearSelectedTiles();

    public abstract void drag(double x, double y);

    public abstract void setStartDrag(double x, double y);

    public abstract void place();

    protected abstract boolean checkBounds(int[] ints);

}