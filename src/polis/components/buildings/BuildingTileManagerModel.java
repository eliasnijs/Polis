package polis.components.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.BuildingTileView;
import java.util.ArrayList;
import java.util.List;

public class BuildingTileManagerModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int mode;
    private BuildingTileView addView;

    private final int gridSize;
    private final int cellSize;

    private final BuildingTileView[][] tiles;

    public BuildingTileManagerModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        tiles = new BuildingTileView[gridSize][gridSize];
    }

    public BuildingTileModel getTile(int row, int column){
        return tiles[row][column].getModel();
    }

    public BuildingTileView[][] getTiles(){
        return tiles;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public BuildingTileView getAddView() {
        return addView;
    }

    public void setTile(BuildingTileModel tile, int row, int column){
        BuildingTileView b = new BuildingTileView();
        tiles[row][column] = b;
        if (tile.getSize() == 2) {
            b.setViewOrder(-row-1 - column-1 - 1.0);
            tiles[row][column+1] = b;
            tiles[row+1][column] = b;
            tiles[row+1][column+1] = b;
        } else {
            b.setViewOrder(-row - column - 1.0);
        }
        b.setModel(tile);
        b.getModel().fireInvalidationEvent();
        mode = 0;
        addView = b;
        fireInvalidationEvent();
    }

    public void deleteTile(int row, int column){
        mode = 1;
        addView = tiles[row][column];
        fireInvalidationEvent();
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    public void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
}
