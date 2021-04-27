package polis.components.playingfield.buildings.tiles;

import polis.components.playingfield.buildings.tiles.buildings.BuildingProperties;

public abstract class Building extends BuildingTileModel {

    private final BuildingProperties properties;

    private int level;
    private int occupancy;
    private double capacity;
    private final int[] levelInterval;

    public Building(int row, int column, String name, String function, BuildingProperties buildingProperties) {
        super(row, column, name, function, 2);
        this.properties = buildingProperties;
        level = 0;
        levelInterval = new int[]{-1,-1};
        occupancy = 0;
        capacity = properties.getInitialCapacity();
    }

    public void Update() {
        if (capacity < levelInterval[0] && level > 1) {
            level -= 1;
            loadLevel();
        } else if (capacity >= levelInterval[1]) {
            level += 1;
            loadLevel();
        }
    }

    public void loadLevel () {
        levelInterval[0] = properties.getLevelChanges(level + level - 2);
        levelInterval[1] = properties.getLevelChanges(level + level - 1);
        setImage(getName() + "-" + level);
    }

    @Override
    public int getOccupancy() {
        return occupancy;
    }

    @Override
    public double getCapacity() {
        return capacity;
    }

    public void factorCapacity(double factor) {
        capacity = capacity*factor;
        if (capacity < properties.getMinimumCapacity()) {
            capacity = properties.getMinimumCapacity();
        }
        Update();
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public void plusOccupancy() {
        occupancy += 1;
        Update();
    }

    @Override
    public void minOccupancy() {
        occupancy -= 1;
    }

}