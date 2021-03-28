package polis.components.playingField.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingField.TileModel;

import java.util.ArrayList;
import java.util.List;


public class CursorTileModel extends TileModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private int size;
    private String color;

    public CursorTileModel(String color, int row, int column, int size, int cellSize){
        super(row, column, size, cellSize);
        this.color = color;
    }

    // Getters
    public String getColor(){ return color; }

    // Setters
    public void setColor(String color){
        this.color = color;
        fireInvalidationEvent();
    }
}
