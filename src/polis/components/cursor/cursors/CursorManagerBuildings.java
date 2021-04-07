package polis.components.cursor.cursors;

import polis.components.buildings.BuildingTileManagerModel;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.tiles.Building;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileView;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorManagerBuildings extends CursorManager {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#D95B6699",
            "AVAILABLE", "#59D98699",
            "UNSELECTED","#FFFFFF00"
    );

    public CursorManagerBuildings(int gridSize, int cellSize, BuildingTileManagerModel buildingField, ArrayList<int[]> selected, CursorTileView[][] tiles){
        super(gridSize, cellSize, buildingField, selected, tiles);
    }

    @Override
    public void drag(double x, double y) { }

    @Override
    public void setStartDrag(double x, double y) { }

    @Override
    public void place() {
        placeTiles();
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(colors.get("UNSELECTED"));
        }
        selected.clear();
    }

    public boolean checkAvailable(){
        for (int[] c : selected) {
            String s = getTileModel(c[0],c[1]).getStatus();
            if (s.equals("UNAVAILABLE")) {
               return false;
            }
        }
        return !selected.isEmpty();
    }

    public void colorSelectedTiles(){
        String color = checkAvailable()? colors.get("AVAILABLE") : colors.get("UNAVAILABLE");
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(color);
        }
    }

    public boolean checkBounds(int[] c){
        return (c[0] >= 0 && c[0] < getGridSize()-1 && c[1] >= 0 && c[1] < getGridSize()-1);
    }

    public void addActiveTile(int[] c){
        if (checkBounds(c)){
            Collections.addAll(selected, c, new int[]{c[0],c[1]+1}, new int[]{c[0]+1,c[1]}, new int[]{c[0]+1,c[1]+1});
        }
    }

    public void placeTiles(){
        if (checkAvailable()) {
            int[] c =  selected.get(0);
            if (checkBounds(c)) {
                BuildingTileModel b = new Building(new ImageLoader(),c[0],c[1],getCellSize(),getTool());
                getBuildingField().setTile(b,b.getRow(),b.getColumn());
            }
            for (int[] c2 : selected) {
                getTileModel(c2[0],c2[1]).setStatus("UNAVAILABLE");
            }
        }
        clearSelectedTiles();
    }

}
