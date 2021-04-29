package polis.components;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * Algemene tile klasse die als template dient voor de tiles van het gebouwen-veld en van het cursor-veld
 * **/
public abstract class TileModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int row;
    private int column;

    public TileModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setPosition(int row, int column) {
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

    protected void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
