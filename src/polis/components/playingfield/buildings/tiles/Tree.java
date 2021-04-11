package polis.components.playingfield.buildings.tiles;

import polis.components.playingfield.buildings.BuildingTileModel;

public class Tree extends BuildingTileModel {

    private static final int amountOfOptions = 4;

    public Tree(int row, int column, String name, int size) {
        super(row, column, name, size);
        int level = selectRandom();
        setImage(getName()+"-"+ level);
    }

    public int selectRandom(){
        return (int) (Math.random()*(amountOfOptions));
    }

}
