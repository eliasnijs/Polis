package polis.components.buildings.buildingtile;

import javafx.scene.image.Image;
import polis.components.TileModel;
import polis.other.ImageLoader;

public abstract class BuildingTileModel extends TileModel {

    private final ImageLoader imageLoader;
    private Image image;
    private final String name;
    private final int size;
    private boolean destructible;

    public BuildingTileModel(ImageLoader imageLoader, int row, int column, int cellSize, String name, int size){
        super(row, column, cellSize);
        this.imageLoader = imageLoader;
        this.image = imageLoader.getImage(name+"-0");
        this.name = name;
        this.size = size;
        destructible = true;
    }

    public void Update() { }

    public Image getImage(){
        return image;
    }

    public void setImage(String name) {
        Image image = imageLoader.getImage(name);
        if(image != this.image){
            this.image = image;
            fireInvalidationEvent();
        }
    }

    public String getName(){
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

}