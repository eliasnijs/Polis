package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die de visualisatie van alle tiles coordineert.
 * **/
public class CursorField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int pendingMode;
    private CursorTileView pendingView;

    public void setTile(CursorTileModel tile) {
        CursorTileView b = new CursorTileView(tile);
        pendingMode = 0;
        pendingView = b;
        fireInvalidationEvent();
    }

    public void deleteTiles() {
        pendingMode = 1;
        fireInvalidationEvent();
    }

    public int getPendingMode() {
        return pendingMode;
    }

    public CursorTileView getPendingView() {
        return pendingView;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
