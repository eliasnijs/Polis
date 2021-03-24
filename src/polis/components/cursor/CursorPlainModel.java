package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import polis.components.plane.tile.TileModel;
import polis.other.ImageLoader;

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

    public CursorPlainModel(){
        tiles = new CursorTileModel[GRID_SIZE][GRID_SIZE];
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                tiles[i][j] = new CursorTileModel(colors.get("UNSELECTED"), i, j, 1, CELL_SIZE);
            }
        }
    }

    // Handle single tile
    public CursorTileModel getTile(int row, int column){
        return tiles[row][column];
    }
    public void setTile(CursorTileModel tile, int row, int column){
        if(tile != this.tiles[row][column] ){
            tiles[row][column]  = tile;
            fireInvalidationEvent();
        }
    }

    // Handles tiles
    public CursorTileModel[][] getTiles() {
        return tiles;
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
