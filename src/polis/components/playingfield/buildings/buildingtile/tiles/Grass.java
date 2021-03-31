package polis.components.playingfield.buildings.buildingtile.tiles;

import polis.components.playingfield.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Grass extends BuildingTileModel {

    public Grass(ImageLoader imageLoader, int row, int column, int size, int cellSize) {
        super(imageLoader, row, column, size, cellSize, "grass");
    }

}
