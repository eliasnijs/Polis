package polis.components.playingfield.buildings.tiles;

public abstract class Building extends BuildingTileModel {

    private static final int[] capacities = new int[]{0, 0, 0, 0};
    private int level;
    private int occupancy;
    private int capacity;

    public Building(int row, int column, String name, String function) {
        super(row, column, name, function, 2);
        level = 0;
        occupancy = 0;
        capacity = capacities[level];
    }

    public void Update() {
        level = (level + 1) % (capacities.length);
        setImage(getName() + "-" + level);
    }

}