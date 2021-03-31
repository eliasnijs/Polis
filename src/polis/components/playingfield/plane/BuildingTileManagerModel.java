package polis.components.playingfield.plane;

import javafx.beans.InvalidationListener;
import polis.components.playingfield.plane.tiles.Grass;
import polis.other.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class BuildingTileManagerModel {

    private ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final int gridSize;
    private final int cellSize;

    private final BuildingTileView[][] tiles;

    public BuildingTileManagerModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.imageLoader = new ImageLoader();
        tiles = new BuildingTileView[gridSize][gridSize];
        for(int i=0; i<gridSize; i++){
            for(int j=0; j<gridSize; j++){
                tiles[i][j] = new BuildingTileView(new Grass(imageLoader, i, j, 1, cellSize));
            }
        }
    }

    public BuildingTileModel getTile(int row, int column){
        return tiles[row][column].getModel();
    }

    public BuildingTileView[][] getTiles(){
        return tiles;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setTile(BuildingTileModel tile, int row, int column){
        tiles[row][column].setModel(tile);
        tiles[row][column].getModel().fireInvalidationEvent();
    }

}
