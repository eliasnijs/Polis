package polis.components.playingfield.cursor;

import polis.components.playingfield.plane.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Map;

public class Cursor {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#D95B6699",
            "AVAILABLE", "#59D98699",
            "UNSELECTED","#FFFFFF00"
    );

    private final int gridSize;
    private final int cellSize;

    private final ImageLoader imageLoader = new ImageLoader();

    private final CursorTileView[][] tiles;
    private final BuildingTileManagerModel buildingField;

    private int size;
    private ArrayList<int[]> selected;

    public Cursor(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.size = 1;
        buildingField = new BuildingTileManagerModel(gridSize, cellSize);

        selected = new ArrayList<>();

        tiles = new CursorTileView[this.gridSize][this.gridSize];
        for(int i = 0; i< this.gridSize; i++){
            for(int j = 0; j< this.gridSize; j++){
                tiles[i][j] = new CursorTileView(new CursorTileModel(colors.get("UNSELECTED"), i, j, this.cellSize));
            }
        }
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

    public CursorTileModel getTileModel(int row, int column){
        return tiles[row][column].getModel();
    }

    public int[] getTileFromCoordinates(double x, double y){
        int column = (int) ((x/ cellSize)/2 + y/ cellSize - 1 + 0.5);
        int row = (int) ((-x/ cellSize)/2 + y/ cellSize + 0.5);
        return new int[]{row,column};
    }

    public void switchSize(){
        size = size+1;
        if(this.size > 2){
            this.size = 1;
        }
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(colors.get("UNSELECTED"));
        } selected.clear();
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        int[] tile = getTileFromCoordinates(x, y);
        if(tile != null){
            addActiveTile(tile);
            colorSelectedTiles();
        }
    }

}
