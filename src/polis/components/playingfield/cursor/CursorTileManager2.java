package polis.components.playingfield.cursor;

import polis.components.playingfield.plane.BuildingTileManagerModel;
import polis.components.playingfield.plane.tiles.Road;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Map;

public class CursorTileManager2 {

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
    private int size = 1;


    private ArrayList<int[]> selected;


    public CursorTileManager2(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
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
        int column = (int) ((x/ cellSize)/2 + y/ cellSize - size + 0.5);
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

    public boolean checkAvailable(){
        for (int[] c : selected) {
            String s = getTileModel(c[0],c[1]).getStatus();
            if (s.equals("UNAVAILABLE")) {
               return false;
            }
        } return true;
    }

    public void colorSelectedTiles(){
        String color;
        if (checkAvailable()) {
            color = colors.get("AVAILABLE");
        } else {
            color = colors.get("UNAVAILABLE");
        }
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(color);
        }
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        int[] coords = getTileFromCoordinates(x,y);
        addActiveTile(coords);
        colorSelectedTiles();
    }

    public void addActiveTile(int[] c){
        selected.add(c);
        selected.add(new int[]{c[0],c[1]+1});
        selected.add(new int[]{c[0]+1,c[1]});
        selected.add(new int[]{c[0]+1,c[1]+1});
    }

    public void mousePressed(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setStatus("UNAVAILABLE");
        }
        setBuildingTile(selected.get(0)[0],selected.get(0)[1]);
    }

    public void setBuildingTile(int x, int y){
        Road road = new Road(imageLoader,x, y, 1, cellSize, "road-1");
        buildingField.setTile(road,x,y);
    }

    public CursorTileView[][] getTiles() {
        return tiles;
    }
}
