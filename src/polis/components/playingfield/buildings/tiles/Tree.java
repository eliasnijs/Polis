package polis.components.playingfield.buildings.tiles;

/**
 * Een boom klasse.
 * **/
public class Tree extends BuildingTileModel {

    private static final int amountOfOptions = 4;

    public Tree(int row, int column) {
        super(row, column, "tree", 1);
        setImage(getName() + "-" + (int) (Math.random() * (amountOfOptions)));
    }

}