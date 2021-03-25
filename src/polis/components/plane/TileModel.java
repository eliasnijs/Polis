package polis.components.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TileModel implements Observable {

    private final ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final int row;
    private final int column;
    private final int cellSize;

    private Image image;
    private int size;

    // initialize
    public TileModel(ImageLoader imageLoader, int row, int column, int size, int cellSize){
        this.imageLoader = imageLoader;
        this.image = imageLoader.getImage("grass");
        this.row = row;
        this.column = column;
        this.size = size;
        this.cellSize = cellSize;
    }

    // Getters
    public Image getImage() {
        return image; 
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public int getCellSize() {
        return cellSize;
    }

    // Setters
    public void setImage(Image image) {
        if(image != this.image){
            this.image = image;
            fireInvalidationEvent();
        }
    }

    // Handle external communication
    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    private void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
    

}
