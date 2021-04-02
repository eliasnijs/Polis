package polis.components.buildings.buildingtile.tiles;

import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Road extends BuildingTileModel {


    public Road(ImageLoader imageLoader, int row, int column, int cellSize) {
        super(imageLoader, row, column, cellSize, "road", 1);
    }

}
