package polis.components.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TileManagerModel implements Observable {

    private static final int GRID_SIZE = 32;
    private static final int CELL_SIZE = 64;

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private TileModel[][] tiles;

    // initialize, fill the grid with new tiles
    public TileManagerModel(ImageLoader imageLoader){
        tiles = new TileModel[GRID_SIZE][GRID_SIZE];
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                tiles[i][j] = new TileModel(imageLoader, i, j, 1, CELL_SIZE);
            }
        }
    }


    // Handle all tiles
    public TileModel[][] getTiles(){
        return tiles;
    }
    public void setTiles(TileModel[][] tiles){
        if(tiles != this.tiles){
            this.tiles = tiles;
            fireInvalidationEvent();
        }
    }

    // Handle single tile
    public TileModel getTile(int row, int column){
        return tiles[row][column];
    }
    public void setTile(TileModel tile, int row, int column){
        if(tile != this.tiles[row][column] ){
            tiles[row][column]  = tile;
            fireInvalidationEvent();
        }
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
