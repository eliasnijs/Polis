package polis.components.playingfield.plane.tiles;

import polis.components.playingfield.plane.BuildingTileModel;
import polis.other.ImageLoader;

public class Road extends BuildingTileModel {

    private String name;

    public Road(ImageLoader imageLoader, int row, int column, int size, int cellSize, String name) {
        super(imageLoader, row, column, size, cellSize, name);
    }

}
