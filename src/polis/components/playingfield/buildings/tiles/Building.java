package polis.components.playingfield.buildings.tiles;

import polis.components.playingfield.buildings.tiles.buildings.BuildingProperties;

/**
 * Klasse die een gebouw representeert.
 * **/
public abstract class Building extends BuildingTileModel {

    private final BuildingProperties properties;

    private int level;
    private int occupancy;
    private double capacity;
    private final int[] levelInterval;

    public Building(int row, int column, String name, BuildingProperties buildingProperties) {
        super(row, column, name, 2);
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

    public int getOccupancy() {
        return occupancy;
    }

    public double getCapacity() {
        return capacity;
    }


    private void loadLevel() {
        levelInterval[0] = properties.getLevelChanges(level + level - 2);
        levelInterval[1] = properties.getLevelChanges(level + level - 1);
        setImage(getName() + "-" + level);
    }

    public void factorCapacity(double factor) {
        capacity = Math.min(capacity*factor, 1000000);
        capacity = Math.max(properties.getMinimumCapacity(), capacity*factor);
        Update();
    }

    public void addOccupancy(int amount) {
        occupancy += amount;
        Update();
    }

}