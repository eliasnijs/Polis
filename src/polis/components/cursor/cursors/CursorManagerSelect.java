package polis.components.cursor.cursors;

import polis.components.Manager;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.BuildingTileView;
import polis.components.buildings.buildingtile.tiles.Road;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class CursorManagerSelect extends CursorManager {

    private static final Map<String,String> colors = Map.of(
            "bulldoze", "#D95B6699",
            "select", "#FFFFFF"
    );

    private static final int strokeWidth = 7;
    private final Manager manager;

    private final Map<String, Runnable> tools = Map.of(
            "select",this::select,
            "bulldoze",this::bulldoze
    );

    public CursorManagerSelect(int gridSize, int cellSize, BuildingTileManagerModel buildingField, ArrayList<int[]> selected, CursorTileView[][] tiles, Manager manager1){
        super(gridSize, cellSize, buildingField, selected, tiles);
        this.manager = manager1;
    }

    public void drag(double x, double y) { }

    public void setStartDrag(double x, double y) { }

    public void place() {
        tools.get(getTool()).run();
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setStroke("#00000000",0);
        } selected.clear();
    }

    public void colorSelectedTiles(){
        String color = colors.get(getTool());
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setStroke(color,strokeWidth);
        }
    }

    public void addActiveTile(int[] c){
        if (checkBounds(c)) {
            selected.add(c);
        }
    }

    public boolean checkBounds(int[] c){
        return ( c[0] >= 0 && c[0] < getGridSize() && c[1] >= 0 && c[1] < getGridSize());
    }

    public void bulldoze(){
        for (int[] c : selected) {
            BuildingTileView v = getBuildingField().getTiles()[c[0]][c[1]];
            if (v != null) {
                BuildingTileModel m = v.getModel();
                if(m.isDestructible()){
                    getBuildingField().deleteTile(c[0],c[1]);
                    for (int i = 0; i < getGridSize(); i += 1) {
                        for (int j = 0; j < getGridSize(); j += 1) {
                            if (getBuildingField().getTiles()[i][j] == v) {
                                getBuildingField().getTiles()[i][j] = null;
                                getTiles()[i][j].getModel().setStatus("AVAILABLE");
                            }
                        }
                    }
                    if (m.getName().equals("road")) {
                        CursorManagerRoads roads = (CursorManagerRoads) manager.getManager(1);
                        boolean[] adjacent = roads.checkNeighbours(c[0],c[1]);
                        ArrayList<int[]> pos = new ArrayList<>();
                        Collections.addAll(pos,new int[]{-1,0},new int[]{0,1},new int[]{1,0},new int[]{0,-1});
                        for (int i=0; i<pos.size(); i++) {
                            int[] s = pos.get(i);
                            if (adjacent[i]) {
                                boolean[] adj = roads.checkNeighbours(c[0]+s[0],c[1]+s[1]);
                                getBuildingField().getTiles()[c[0]+s[0]][c[1]+s[1]].getModel().setNeighbours(adj);
                            }
                        }
                    }
                }
            }
        }
    }

    public void select(){
        for (int[] c : selected) {
            BuildingTileView view = getBuildingField().getTiles()[c[0]][c[1]];
            if (view != null) {
                view.getModel().Update();
            }
        }
    }

}
