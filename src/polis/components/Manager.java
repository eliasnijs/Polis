package polis.components;

import polis.components.buildings.buildingtile.tiles.Road;
import polis.components.cursor.CursorManagerView;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursors.CursorManagerBuildings;
import polis.components.cursor.cursors.CursorManagerSelect;
import polis.components.cursor.cursors.CursorManagerRoads;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;

public class Manager {

    private final ImageLoader imageLoader;

    private final int gridSize;
    private final int cellSize;

    private final CursorTileView[][] tiles;
    private final BuildingTileManagerModel buildingField;

    private final ArrayList<CursorManager> managers;
    private CursorManager activeManager;

    private CursorManagerView view;
    
    public Manager(int gridSize, int cellSize){
        this.imageLoader = new ImageLoader();
        this.gridSize = gridSize;
        this.cellSize = cellSize;

        this.tiles = new CursorTileView[gridSize][gridSize];
        ArrayList<int[]> selected = new ArrayList<>();

        for(int i = 0; i< this.gridSize; i++){
            for(int j = 0; j< this.gridSize; j++){
                tiles[i][j] = new CursorTileView(new CursorTileModel(i, j, this.cellSize));
            }
        }

        this.buildingField = new BuildingTileManagerModel(gridSize,cellSize);
        CursorManagerBuildings buildings = new CursorManagerBuildings(gridSize, cellSize, buildingField, selected, tiles);
        CursorManagerRoads roads = new CursorManagerRoads(gridSize, cellSize, buildingField, selected, tiles);
        CursorManagerSelect select = new CursorManagerSelect(gridSize, cellSize, buildingField, selected, tiles);

        managers = new ArrayList<>();
        managers.add(buildings);
        managers.add(roads);
        managers.add(select);

        activeManager = select;
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
        activeManager.clearSelectedTiles();
        this.activeManager = managers.get(i);
        view.setModel(activeManager);
    }

    public void setActiveManager(int i, String tool) {
        activeManager.clearSelectedTiles();
        this.activeManager = managers.get(i);
        activeManager.setTool(tool);
        view.setModel(activeManager);
    }

    public void setStartupTiles(){
        for (int i=0; i<10; i+=1) {
            Road r = new Road(imageLoader, i, gridSize/2-1, cellSize);
            r.setDestructible(false);
            buildingField.setTile(r,i, gridSize/2-1);
            tiles[i][gridSize/2-1].getModel().setStatus("UNAVAILABLE");
        }
    }
}
