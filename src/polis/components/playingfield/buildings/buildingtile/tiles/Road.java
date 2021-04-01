package polis.components.playingfield.buildings.buildingtile.tiles;

import polis.components.playingfield.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Road extends BuildingTileModel {

    private String name;

    public Road(ImageLoader imageLoader, int row, int column, int cellSize, String name) {
        super(imageLoader, row, column, cellSize, "road-0");
    }

}
