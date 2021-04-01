package polis.components.playingfield.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.buildings.buildingtile.BuildingTileModel;
import polis.components.playingfield.buildings.buildingtile.BuildingTileView;
import polis.components.playingfield.buildings.buildingtile.tiles.Grass;
import polis.other.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BuildingTileManagerModel implements Observable {

    private ImageLoader imageLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private BuildingTileView addView;

    private final int gridSize;
    private final int cellSize;

    private final BuildingTileView[][] tiles;

    public BuildingTileManagerModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.imageLoader = new ImageLoader();
        tiles = new BuildingTileView[gridSize][gridSize];
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

    public BuildingTileView getAddView() {
        return addView;
    }

    public void setTile(BuildingTileModel tile, int row, int column){
        BuildingTileView b = new BuildingTileView();
        b.setModel(tile);
        b.getModel().fireInvalidationEvent();
        addView = b;
        tiles[row][column] = b;
        fireInvalidationEvent();
    }

    // External communication with view
    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    public void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
}
