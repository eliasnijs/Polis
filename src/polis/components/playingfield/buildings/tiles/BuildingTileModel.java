package polis.components.playingfield.buildings.tiles;

import javafx.scene.image.Image;
import polis.components.TileModel;
import polis.datakeepers.FieldData;

public abstract class BuildingTileModel extends TileModel {

    private final String name;
    private final int size;
    private Image image;
    private boolean destructible;

    public BuildingTileModel(int row, int column, String name, int size) {
        super(row, column);
        this.image = FieldData.getImageLoader().getImage(name + "-0");
        this.name = name;
        this.size = size;
        destructible = true;
    }

    public void Update() { }

    public Image getImage() {
        return image;
    }

    public void setImage(String name) {
        this.image = FieldData.getImageLoader().getImage(name);
        fireInvalidationEvent();
    }

    public String getName() {
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

    public void setNeighbours(boolean[] adj) { }
}