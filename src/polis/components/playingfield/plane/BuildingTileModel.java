package polis.components.playingfield.plane;

import javafx.scene.image.Image;
import polis.components.playingfield.TileModel;
import polis.other.ImageLoader;

import java.util.Locale;

public class BuildingTileModel extends TileModel {

    private final ImageLoader imageLoader;

    private Image image;
    private int size;

    // initialize
    public BuildingTileModel(ImageLoader imageLoader, int row, int column, int size, int cellSize, String name){
        super(row, column, cellSize);
        this.imageLoader = imageLoader;
        this.image = imageLoader.getImage(name);
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
