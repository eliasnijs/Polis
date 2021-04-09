package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.BuildingTileView;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;

import java.util.ArrayList;
import java.util.List;

public class CursorFieldModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final int gridSize;
    private final int cellSize;

    private int pendingMode;
    private CursorTileView pendingView;

    private CursorTileView[][] tiles;

    public CursorFieldModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        tiles = new CursorTileView[gridSize][gridSize];
    }

    public CursorTileView[][] getTiles(){
        return tiles;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setTile(CursorTileModel tile, int row, int column, int size){
        CursorTileView b = new CursorTileView(tile);
        tiles[row][column] = b;
        if (size == 2) {
            tiles[row][column + 1] = b;
            tiles[row + 1][column] = b;
            tiles[row + 1][column + 1] = b;
        }
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

    public CursorTileView getPendingView(){
        return  pendingView;
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
