package polis.components.buildings.buildingtile.tiles;

import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Tree extends BuildingTileModel {

    private static final int amountOfOptions = 4;

    public Tree(ImageLoader imageLoader, int row, int column, int cellSize, String name, int size) {
        super(imageLoader, row, column, cellSize, name, size);
        int level = selectRandom();
        setImage(getName()+"-"+ level);
    }

    public int selectRandom(){
        return (int) (Math.random()*(amountOfOptions));
    }

}
