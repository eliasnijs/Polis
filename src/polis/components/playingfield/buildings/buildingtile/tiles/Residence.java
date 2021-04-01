package polis.components.playingfield.buildings.buildingtile.tiles;

import polis.components.playingfield.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Residence extends BuildingTileModel {

    private final String name;
    private static final int[] capacities = new int[]{0,0,0,0};

    private int level;

    private int occupancy;
    private int capacity;

    public Residence(ImageLoader imageLoader, int row, int column, int cellSize){
        super(imageLoader, row, column, cellSize, "residence-0");

        name = "residence";
        level = 0;
        occupancy = 0;
        capacity = capacities[level];
    }

    public void Update(){
        level = (level+1)%(capacities.length);
        setImage(name+"-"+level);
    }

}
