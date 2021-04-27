package polis.components.playingfield.buildings.tiles;

public class Tree extends BuildingTileModel {

    private static final int amountOfOptions = 4;

    public Tree(int row, int column) {
        super(row, column, "tree", "decoration", 1);
        int level = selectRandom();
        setImage(getName() + "-" + level);
    }

    public int selectRandom() {
        return (int) (Math.random() * (amountOfOptions));
    }



}
