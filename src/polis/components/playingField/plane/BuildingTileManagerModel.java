package polis.components.playingField.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BuildingTileManagerModel implements Observable {

    private static final int GRID_SIZE = 32;
    private static final int CELL_SIZE = 64;

    private ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final BuildingTileView[][] tiles;

    public BuildingTileManagerModel(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        tiles = new BuildingTileView[GRID_SIZE][GRID_SIZE];
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                tiles[i][j] = new BuildingTileView(new BuildingTileModel(imageLoader, i, j, 1, CELL_SIZE));
            }
        }
    }

    // Getters
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public BuildingTileView getTile(int row, int column){
        return tiles[row][column];
    }

    public BuildingTileView[][] getTiles(){
        return tiles;
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }

    public static int getCellSize() {
        return CELL_SIZE;
    }

    // Setters
    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setTile(BuildingTileModel tile, int row, int column){
        if(tile != tiles[row][column].getModel() ){
            tiles[row][column].setModel(tile);
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
