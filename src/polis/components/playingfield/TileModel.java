package polis.components.playingfield;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public class TileModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final int row;
    private final int column;
    private final int cellSize;

    public TileModel(int row, int column, int cellSize){
        this.row = row;
        this.column = column;
        this.cellSize = cellSize;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int[] gridToCoordinates(){
        int x = cellSize * (1 - row  + column);
        int y = cellSize * (row + column) / 2;
        return new int[]{x,y};
    }

    // External communication with view
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
