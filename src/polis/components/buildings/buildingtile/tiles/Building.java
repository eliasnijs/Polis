package polis.components.buildings.buildingtile.tiles;

import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Building extends BuildingTileModel {

    private int level;
    private int occupancy;
    private int capacity;
    private static final int[] capacities = new int[]{0,0,0,0};

    public Building(ImageLoader imageLoader, int row, int column, int cellSize, String name){
        super(imageLoader, row, column, cellSize, name, 2);
        level = 0;
        occupancy = 0;
        capacity = capacities[level];
    }

    public void Update(){
        level = (level+1)%(capacities.length);
        setImage(getName()+"-"+level);
    }

}