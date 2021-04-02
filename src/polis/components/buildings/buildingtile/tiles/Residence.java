package polis.components.buildings.buildingtile.tiles;

import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Residence extends Building {

    private static final String name = "residence";

    public Residence(ImageLoader imageLoader, int row, int column, int cellSize) {
        super(imageLoader, row, column, cellSize, name);
    }

}
