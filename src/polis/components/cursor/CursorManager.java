package polis.components.cursor;

import polis.components.buildings.BuildingTileManagerModel;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class CursorManager {

    private String tool;

    private final int gridSize;
    private final int cellSize;

    private final CursorTileView[][] tiles;
    private final BuildingTileManagerModel buildingField;
    public ArrayList<int[]> selected;

    public CursorManager(int g, int c, BuildingTileManagerModel bf, ArrayList<int[]> s, CursorTileView[][] t){
        this.gridSize = g;
        this.cellSize = c;
        this.buildingField = bf;
        this.selected = s;
        this.tiles = t;
    }

    public BuildingTileManagerModel getBuildingField() {
        return buildingField;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public CursorTileView[][] getTiles(){
        return tiles;
    }

    public CursorTileModel getTileModel(int row, int column){
        return tiles[row][column].getModel();
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

    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void clearSelectedTiles();

    protected abstract void drag(double x, double y);

    protected abstract void setStartDrag(double x, double y);

    public abstract void place() throws FileNotFoundException;

    protected abstract boolean checkBounds(int[] ints);

}