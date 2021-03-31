package polis.components.playingfield.cursor;

import polis.components.playingfield.buildings.buildingtile.tiles.Road;
import polis.components.playingfield.cursor.cursors.CursorTileManagerBuildings;
import polis.components.playingfield.cursor.cursors.CursorTileManagerRoads;
import polis.components.playingfield.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.cursor.cursortile.CursorTileView;
import polis.components.playingfield.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;

public class CursorManager<T> {

    private ImageLoader imageLoader;

    private final int gridSize;
    private final int cellSize;

    private final CursorTileView[][] tiles;
    private final ArrayList<int[]> selected;
    private final BuildingTileManagerModel buildingField;

    private final CursorTileManagerBuildings buildings;
    private final CursorTileManagerRoads roads;

    private ArrayList<CursorTileManager> managers;
    private CursorTileManager activeManager;

    private CursorManagerView view;
    
    public CursorManager(int gridSize, int cellSize){
        this.imageLoader = new ImageLoader();
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.tiles = new CursorTileView[gridSize][gridSize];
        this.selected = new ArrayList<>();
        this.buildingField = new BuildingTileManagerModel(gridSize,cellSize);

        for(int i = 0; i< this.gridSize; i++){
            for(int j = 0; j< this.gridSize; j++){
                tiles[i][j] = new CursorTileView(new CursorTileModel("#00000000", i, j, this.cellSize));
            }
        }

        this.buildings = new CursorTileManagerBuildings(gridSize,cellSize,buildingField,selected,tiles);
        this.roads = new CursorTileManagerRoads(gridSize, cellSize, buildingField,selected,tiles);

        managers = new ArrayList<>();

        managers.add(buildings);
        managers.add(roads);

        activeManager = buildings;
    }

    public CursorTileManager getActiveManager() {
        return activeManager;
    }

    public BuildingTileManagerModel getBuildingField() {
        return buildingField;
    }

    public void setView(CursorManagerView view){
        this.view = view;
    }

    public void setActiveManager(int i) {
        System.out.println(managers.get(i));
        this.activeManager = managers.get(i);
        view.setModel(activeManager);
    }

    public void place(){
        for (int[] t : selected) {
            activeManager.getTileModel(t[0],t[1]).setStatus("UNAVAILABLE");
            Road road = new Road(imageLoader, t[0], t[1], 1,cellSize,"road-1");
            buildingField.setTile(road,t[0],t[1]);
        }
        activeManager.clearSelectedTiles();
    }

}
