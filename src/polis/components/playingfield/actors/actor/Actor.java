package polis.components.playingfield.actors.actor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.List;

public class Actor implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private String color;
    private int[] position;

    public Actor(int row, int column) {
        position = new int[]{row, column};
        color = "#ffffff";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int[] getCoords() {
        return GridCoordsConverter.gridToCoords(position);
    }

    public void setPosition(int[] position) {
        this.position = position;
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
