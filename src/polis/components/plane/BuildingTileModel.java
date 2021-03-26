package polis.components.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.components.TileModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BuildingTileModel extends TileModel implements Observable {

    private final ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private Image image;
    private int size;

    // initialize
    public BuildingTileModel(ImageLoader imageLoader, int row, int column, int size, int cellSize){
        super(row, column, size, cellSize);
        this.imageLoader = imageLoader;
        this.image = imageLoader.getImage("grass");
    }

    // Getters
    public Image getImage(){
        return image;
    }

    // Setters
    public void setImage(Image image) {
        if(image != this.image){
            this.image = image;
            fireInvalidationEvent();
        }
    }

}
