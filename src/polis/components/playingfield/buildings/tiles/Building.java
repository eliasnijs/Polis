package polis.components.playingfield.buildings.tiles;

import polis.components.playingfield.buildings.BuildingTileModel;

public class Building extends BuildingTileModel {

    private int level;
    private int occupancy;
    private int capacity;
    private static final int[] capacities = new int[]{0,0,0,0};

    public Building(int row, int column, String name){
        super(row, column, name, 2);
        level = 0;
        occupancy = 0;
        capacity = capacities[level];
    }

    public void Update(){
        level = (level+1)%(capacities.length);
        setImage(getName()+"-"+level);
    }

}