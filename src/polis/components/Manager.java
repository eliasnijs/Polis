package polis.components;

import polis.components.buildings.buildingtile.tiles.Tree;
import polis.components.buildings.buildingtile.tiles.Road;
import polis.components.cursor.CursorFieldModel;
import polis.components.cursor.CursorFieldView;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursors.CursorManagerBuildings;
import polis.components.cursor.cursors.CursorManagerSelect;
import polis.components.cursor.cursors.CursorManagerRoads;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingFieldModel;
import polis.other.ImageLoader;
import polis.other.Noise;

import java.util.ArrayList;

public class Manager {

    private final ImageLoader imageLoader;

    private final int gridSize;
    private final int cellSize;

    private final CursorFieldModel cursorField;
    private final BuildingFieldModel buildingField;

    private final ArrayList<CursorManager> managers;
    private CursorManager activeManager;
    private CursorFieldView view;
    private boolean trees;

    public Manager(int gridSize, int cellSize) {
        this.trees = true;
        this.imageLoader = new ImageLoader();
        this.gridSize = gridSize;
        this.cellSize = cellSize;

        ArrayList<int[]> selected = new ArrayList<>();

        this.buildingField = new BuildingFieldModel(gridSize,cellSize);
        this.cursorField = new CursorFieldModel(gridSize,cellSize);

        managers = new ArrayList<>();
        managers.add(new CursorManagerBuildings(gridSize, cellSize, buildingField, selected, cursorField));
        managers.add(new CursorManagerRoads(imageLoader, gridSize, cellSize, buildingField, selected, cursorField));
        managers.add(new CursorManagerSelect(gridSize, cellSize, buildingField, selected, cursorField, this));

        activeManager = managers.get(2);
    }

    public CursorManager getActiveManager() {
        return activeManager;
    }

    public BuildingFieldModel getBuildingField() {
        return buildingField;
    }

    public CursorFieldModel getCursorField() {
        return cursorField;
    }

    public void setView(CursorFieldView view){
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

    public CursorManager getManager(int i){
        return managers.get(i);
    }

    public void setStartupTiles(){
        if (trees) {
            setStartupTrees(-.2f);
        }
        setStartupRoads();
    }

    public void setStartupTrees(float roughness){
        Noise noise = new Noise(null, roughness, gridSize, gridSize);
        noise.start();
        boolean[][] noiseMap = noise.toBooleans();
        ArrayList<int[]> locations = new ArrayList<>();
        for (int i=0; i<gridSize;i++) {
            for (int j=0; j<gridSize;j++) {
                if (noiseMap[i][j]) {
                    locations.add(new int[]{i,j});
                }
            }
        }
        locations.removeIf(c -> c[1] == gridSize / 2 -1 && c[0] < gridSize / 2);
        for (int[] c : locations) {
            Tree deco = new Tree(imageLoader, c[0], c[1], cellSize, "tree", 1);
            buildingField.setTile(deco,c[0],c[1]);
        }
    }

    public void setStartupRoads(){
        for (int i=0; i<gridSize/2-1; i+=1) {
            Road r = new Road(imageLoader, i, gridSize/2-1, cellSize, new boolean[]{true,false,true,false});
            r.setDestructible(false);
            buildingField.setTile(r,i,gridSize/2-1);
        }
        Road r = new Road(imageLoader, gridSize/2-1, gridSize/2-1, cellSize, new boolean[]{true,false,false,false});
        r.setDestructible(false);
        buildingField.setTile(r,gridSize/2-1,gridSize/2-1);
    }

    public void reset(){
        for (int i =0; i< gridSize; i++) {
            for (int j =0; j< gridSize; j++) {
                buildingField.deleteTile(i,j);
                buildingField.getTiles()[i][j] = null;
            }
        }
        setStartupTiles();
    }

    public void switchTree() {
        trees = !trees;
    }

    public boolean isTrees() {
        return trees;
    }
}
