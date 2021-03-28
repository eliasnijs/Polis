package polis.components.playingField.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.components.playingField.TileModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BuildingTileModel extends TileModel {

    private final ImageLoader imageLoader;

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
