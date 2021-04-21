package polis.components;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class TileModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int row;
    private int column;

    public TileModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
        fireInvalidationEvent();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int[] gridToCoordinates() {
        return GridCoordsConverter.gridToCoords(new int[]{row, column});
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
