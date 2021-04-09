package polis.components.buildings.buildingtile.tiles;

import polis.components.buildings.BuildingFieldModel;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.other.ImageLoader;

public class Road extends BuildingTileModel {

    /**
     *  ROAD UPDATE
     *
     * right  = 1
     * below  = 2
     * left = 4
     * top = 8
     *
     * **/

    private boolean[] neighbours;
    private BuildingFieldModel tiles;

    public Road(ImageLoader imageLoader, int row, int column, int cellSize, BuildingFieldModel tiles, boolean[] neighbours) {
        super(imageLoader, row, column, cellSize, "road", 1);
        this.neighbours = neighbours;
        this.tiles = tiles;
        Update();
    }

    public void setNeighbours(boolean[] adj){
        neighbours = adj;
        Update();
    };

    public void Update(){
        int level = calculateLevel();
        setImage(getName()+"-"+level);
    }

    public int calculateLevel(){
        int level = 0;
        int[] codes = new int[]{1,2,4,8};
        for (int i=0; i<4; i++) {
            if (neighbours[i]) {
                level += codes[i];
            }
        }
        return level;
    }

}
