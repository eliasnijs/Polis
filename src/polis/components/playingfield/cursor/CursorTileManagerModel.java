package polis.components.playingfield.cursor;

import javafx.beans.InvalidationListener;
import polis.components.playingfield.plane.BuildingTileManagerModel;
import polis.components.playingfield.plane.tiles.Road;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CursorTileManagerModel {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#D95B6699",
            "AVAILABLE", "#59D98699",
            "UNSELECTED","#FFFFFF00"
    );

    private final int gridSize;
    private final int cellSize;

    private final ImageLoader imageLoader = new ImageLoader();

    private final CursorTileView[][] tiles;
    private final ArrayList<CursorTileModel> selected;
    private final BuildingTileManagerModel buildingField;
    private int size = 1;
    private CursorTileModel startOfDrag;

    public CursorTileManagerModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        selected = new ArrayList<>();
        buildingField = new BuildingTileManagerModel(gridSize, cellSize);

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

    public CursorTileView[][] getTiles() {
        return tiles;
    }

    public CursorTileModel getTileFromCoordinates(double x, double y){
        int column = (int) ((x/ cellSize)/2 + y/ cellSize - size + 0.5);
        int row = (int) ((-x/ cellSize)/2 + y/ cellSize + 0.5);
        if(row >= 0 && column >= 0 && row < gridSize && column < gridSize){
            return this.getTileModel(row , column);
        }
        return null;
    }

    // Setters
    public void setTileActive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get(t.getStatus()));
        }
    }

    public void setTileInactive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get("UNSELECTED"));
        }
    }

    public void switchSize(){
        size = size+1;
        if(this.size > 2){
            this.size = 1;
        }
    }

    public void clearSelectedTiles(){
        if (selected.size() > 0) {
            for (CursorTileModel c : selected) {
                setTileInactive(c);
            }
            selected.clear();
        }
    }

    public void colorSelectedTiles(){
        for (CursorTileModel c : selected) {
            setTileActive(c);
        }
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        CursorTileModel tile = getTileFromCoordinates(x, y);
        if(tile != null){
            addActiveTile(tile);
            colorSelectedTiles();
        }
    }

    public void setStartDrag(double x, double y){
        startOfDrag = getTileFromCoordinates(x, y);
    }

    public void selectDragTiles(int x, int y){
        int t = 1;
        if (startOfDrag.getRow() > x) {
            t = -1;
        }
        int i=startOfDrag.getRow();
        while(i != x + t){
            addActiveTile(getTileModel(i, startOfDrag.getColumn()));
            i += t;
        }
        t = 1;
        if (startOfDrag.getColumn() > y) {
            t = -1;
        }
        i=startOfDrag.getColumn();
        while(i != y + t){
            addActiveTile(getTileModel(x, i));
            i += t;
        }
    }

    public void drag(double x, double y){
        CursorTileModel tile = getTileFromCoordinates(x,y);
        clearSelectedTiles();
        if(tile != null && startOfDrag != null){
            selectDragTiles(tile.getRow(),tile.getColumn());
            colorSelectedTiles();
        }
    }

    public void addActiveTile(CursorTileModel tile){
        if (size == 2) {
            if(tile.getRow()+1 >= 0 && tile.getRow()+1 < gridSize && tile.getColumn()+1 >= 0 && tile.getColumn()+1 < gridSize){
                selected.add(tile);
                selected.add(getTileModel(tile.getRow(), tile.getColumn()+1));
                selected.add(getTileModel(tile.getRow()+1, tile.getColumn()));
                selected.add(getTileModel(tile.getRow()+1, tile.getColumn()+1));
            }
        } else {
            selected.add(tile);
        }
    }

    public void mousePressed(){
        for (CursorTileModel t : selected) {
            int x = t.getRow();
            int y = t.getColumn();
            setBuildingTile(x,y);
            t.setStatus("UNAVAILABLE");
        }
        clearSelectedTiles();
    }

    public void setBuildingTile(int x, int y){
        Road road = new Road(imageLoader,x, y, 1, cellSize, "road-1");
        buildingField.setTile(road,x,y);
    }

}
