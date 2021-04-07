package polis.components.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.BuildingTileView;

import java.util.ArrayList;
import java.util.List;

public class BuildingTileManagerModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int pendingMode;
    private BuildingTileView pendingView;

    private final int gridSize;
    private final int cellSize;

    private final BuildingTileView[][] tiles;

    public BuildingTileManagerModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        tiles = new BuildingTileView[gridSize][gridSize];
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

    public BuildingTileView getPendingView() {
        return pendingView;
    }

    public void setTile(BuildingTileModel tile, int row, int column){
        BuildingTileView b = new BuildingTileView(tile);
        tiles[row][column] = b;
        if (tile.getSize() == 2) {
            tiles[row][column + 1] = b;
            tiles[row + 1][column] = b;
            tiles[row + 1][column + 1] = b;
        }
        b.setViewOrder(-row - column - 2*(tile.getSize()-1) - 1.0);
        pendingMode = 0;
        pendingView = b;
        fireInvalidationEvent();
    }

    public void deleteTile(int row, int column){
        pendingMode = 1;
        pendingView = tiles[row][column];
        fireInvalidationEvent();
    }

    public int getPendingMode() {
        return pendingMode;
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