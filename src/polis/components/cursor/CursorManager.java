package polis.components.cursor;

import polis.components.buildings.BuildingFieldModel;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;

import java.util.ArrayList;

public abstract class CursorManager {

    private String tool;

    private final int gridSize;
    private final int cellSize;

    private final CursorFieldModel cursorField;
    private final BuildingFieldModel buildingField;
    public ArrayList<int[]> selected;

    public CursorManager(int g, int c, BuildingFieldModel bf, ArrayList<int[]> s, CursorFieldModel t){
        this.gridSize = g;
        this.cellSize = c;
        this.buildingField = bf;
        this.selected = s;
        this.cursorField = t;
    }

    public BuildingFieldModel getBuildingField() {
        return buildingField;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public CursorTileView[][] getCursorField(){
        return cursorField.getTiles();
    }

    public CursorTileModel getTileModel(int row, int column){
        return cursorField.getTiles()[row][column].getModel();
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
        int column = (int) ((x/cellSize)/2 + y/cellSize - 0.5);
        int row = (int) (-(x/cellSize)/2 + y/cellSize + 0.5);
        return new int[]{row,column};
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        addActiveTile(getTileFromCoordinates(x,y));
        colorSelectedTiles();
    }

    public boolean isAvailable(int[] c) {
        return getBuildingField().getTiles()[c[0]][c[1]] == null;
    }


    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void clearSelectedTiles();

    protected abstract void drag(double x, double y);

    protected abstract void setStartDrag(double x, double y);

    public abstract void place();

    protected abstract boolean checkBounds(int[] ints);

}