package polis.components.playingfield;

import polis.components.playingfield.cursor.CursorManagerView;
import polis.components.playingfield.cursor.CursorManager;
import polis.components.playingfield.cursor.cursors.CursorManagerBuildings;
import polis.components.playingfield.cursor.cursors.CursorManagerRoads;
import polis.components.playingfield.cursor.cursortile.CursorTileModel;
import polis.components.playingfield.cursor.cursortile.CursorTileView;
import polis.components.playingfield.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;

public class Manager {

    private final ImageLoader imageLoader;

    private final int gridSize;
    private final int cellSize;

    private final CursorTileView[][] tiles;
    private final ArrayList<int[]> selected;
    private final BuildingTileManagerModel buildingField;

    private final CursorManagerBuildings buildings;
    private final CursorManagerRoads roads;

    private ArrayList<CursorManager> managers;
    private CursorManager activeManager;

    private CursorManagerView view;
    
    public Manager(int gridSize, int cellSize){
        this.imageLoader = new ImageLoader();
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.tiles = new CursorTileView[gridSize][gridSize];
        this.selected = new ArrayList<>();
        this.buildingField = new BuildingTileManagerModel(gridSize,cellSize);

        for(int i = 0; i< this.gridSize; i++){
            for(int j = 0; j< this.gridSize; j++){
                tiles[i][j] = new CursorTileView(new CursorTileModel("00000000", i, j, this.cellSize));
            }
        }

        this.buildings = new CursorManagerBuildings(gridSize,cellSize,buildingField,selected,tiles);
        this.roads = new CursorManagerRoads(gridSize, cellSize, buildingField,selected,tiles);

        managers = new ArrayList<>();
        managers.add(buildings);
        managers.add(roads);

        activeManager = buildings;
    }

    public CursorManager getActiveManager() {
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
}
