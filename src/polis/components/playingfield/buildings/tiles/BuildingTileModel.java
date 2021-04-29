package polis.components.playingfield.buildings.tiles;

import javafx.scene.image.Image;
import polis.components.TileModel;
import polis.datakeepers.FieldData;

/**
 * BuildingTile klasse. Deze klasse staat in voor het bijhouden van data voor een gebouw, decoratie, straat.
 * In andere woorden, deze klasse staat in voor alle zaken die op het bouw-veld kunnen geplaatst worden.
 * **/
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

    public void Update() { }

    public void setNeighbours(boolean[] adj){}

    public double getCapacity() { return 0; }

    public int getOccupancy() { return 0; }

}