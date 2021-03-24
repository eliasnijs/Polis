package polis.components.tile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TileModel implements Observable {

    private final static String IMAGE_LOCATION = "images/grass.png";

    private final ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private Image image;
    private int row;
    private int column;
    private int size;
    private int cellSize;

    // initialize
    public TileModel(ImageLoader imageLoader, int row, int column, int size, int cellSize){
        this.imageLoader = imageLoader;
        this.image = imageLoader.getImage("grass");
        this.row = row;
        this.column = column;
        this.size = size;
        this.cellSize = cellSize;
    }

    // Handle image
    public Image getImage() {
        return image; 
    }
    public void setImage(Image image) {
        if(image != this.image){
            this.image = image;
            fireInvalidationEvent();
        }
    }

    // return basic data about the tile-model
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
