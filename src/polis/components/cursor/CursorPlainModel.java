package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CursorPlainModel implements Observable {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#FA7232",
            "AVAILABLE", "#11AD9E",
            "UNSELECTED","#FFFFFF00"
    );

    private static final int GRID_SIZE = 32;
    private static final int CELL_SIZE = 64;

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final CursorTileModel[][] tiles;
    private final ArrayList<CursorTileModel> activeTiles;

    private int size = 1;
    private CursorTileModel startOfDrag;

    public CursorPlainModel(){
        activeTiles = new ArrayList<>();
        tiles = new CursorTileModel[GRID_SIZE][GRID_SIZE];
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                tiles[i][j] = new CursorTileModel(colors.get("UNSELECTED"), i, j, 1, CELL_SIZE);
            }
        }
    }

    // Getters
    public CursorTileModel getTile(int row, int column){ return tiles[row][column]; }

    public CursorTileModel[][] getTiles() { return tiles; }

    public ArrayList<CursorTileModel> getActiveTiles(){ return activeTiles; }

    // Get a tile from coordinates
    public CursorTileModel getTileFromCoordinates(double x, double y){
        // Convert coordinates to row and column
        int column = (int) ((((x/CELL_SIZE)-size) + (2*(y/CELL_SIZE)-size))/2 + 0.5);
        int row = (int) ((-((x/CELL_SIZE)-size) + (2*(y/CELL_SIZE)-size))/2 + 0.5);
        // Return the tile if we are certain there is a tile on the coordinates
        if(row >= 0 && column >= 0 && row <= 31 && column <= 31){
            return this.getTile(row , column);
        }
        return null;
    }

    // Setters
    public void setTileActive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get("AVAILABLE"));
        }
    }

    public void setTileInactive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get("UNSELECTED"));
        }
    }

    public void switchSize(){
        if(this.size == 1){
            this.size = 2;
        } else {
            this.size = 1;
        }
    }

    public void clearActiveTiles(){
        if (activeTiles.size() > 0) {
            for (CursorTileModel c : activeTiles) {
                setTileInactive(c);
            }
            activeTiles.clear();
        }
    }

    public void colorActiveTiles(){
        for (CursorTileModel c : activeTiles) {
            setTileActive(c);
        }
    }

    public void hoover(double x, double y) {
        // Remove previous hoover tiles
        clearActiveTiles();
        // Set new hoover tiles
        CursorTileModel tile = getTileFromCoordinates(x, y);
        if(tile == null){
            return;
        }
        if (size == 2) {
            if(tile.getRow()+1 >= 0 && tile.getRow()+1 < 32 && tile.getColumn()+1 >= 0 && tile.getColumn()+1 < 32){
                activeTiles.add(tile);
                activeTiles.add(getTile(tile.getRow(), tile.getColumn()+1));
                activeTiles.add(getTile(tile.getRow()+1, tile.getColumn()));
                activeTiles.add(getTile(tile.getRow()+1, tile.getColumn()+1));
            }
        } else {
            activeTiles.add(tile);
        }
        colorActiveTiles();
    }

    public void setStartDrag(double x, double y){
        startOfDrag = getTileFromCoordinates(x, y);
    }

    public void drag(double x, double y){
        CursorTileModel tile = getTileFromCoordinates(x,y);
        clearActiveTiles();
        // If the current tile is out of bounds
        if(tile == null){
            return;
        }
        // Add the new active tiles
        int t = 1;
        if (startOfDrag.getRow() > tile.getRow()) {
            t = -1;
        }
        int i=startOfDrag.getRow();
        while(i != tile.getRow()+t){
            activeTiles.add(getTile(i, startOfDrag.getColumn()));
            i += t;
        }
        t = 1;
        if (startOfDrag.getColumn() > tile.getColumn()) {
            t = -1;
        }
        i=startOfDrag.getColumn();
        while(i != tile.getColumn()+t){
            activeTiles.add(getTile(tile.getRow(), i));
            i += t;
        }
        colorActiveTiles();
    }

    public void mousePressed(){
        clearActiveTiles();
        System.out.println("placing tiles");
    }


    // Handle external communication
    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    private void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
}
