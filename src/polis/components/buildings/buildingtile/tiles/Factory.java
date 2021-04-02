package polis.components.buildings.buildingtile.tiles;

import polis.other.ImageLoader;

public class Factory extends Building {

    private static final String name = "factory";

    public Factory(ImageLoader imageLoader, int row, int column, int cellSize) {
        super(imageLoader, row, column, cellSize, name);
    }

}
