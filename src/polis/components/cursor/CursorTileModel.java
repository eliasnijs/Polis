package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;


public class CursorTileModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();
    private final int row;
    private final int column;
    private final int cellSize;

    private int size;
    private String color;

    public CursorTileModel(String color, int row, int column, int size, int cellSize){
        this.color = color;
        this.row = row;
        this.column = column;
        this.size = size;
        this.cellSize = cellSize;
    }

    // Getters
    public int getRow() { return row; }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public int getCellSize() {
        return cellSize;
    }

    public String getColor(){ return color; }

    // Setters
    public void setSize(int size){
        this.size =  size;
    }

    public void setColor(String color){
        this.color = color;
        fireInvalidationEvent();
    }


    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }

    private void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
}
