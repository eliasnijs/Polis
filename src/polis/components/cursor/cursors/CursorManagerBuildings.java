package polis.components.cursor.cursors;

import polis.components.buildings.buildingtile.tiles.Building;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
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
    protected void place() {
        placeTiles();
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(colors.get("UNSELECTED"));
        } selected.clear();
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
        String color;
        if (checkAvailable()) {
            color = colors.get("AVAILABLE");
        } else {
            color = colors.get("UNAVAILABLE");
        }
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(color);
        }
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        int[] coords = getTileFromCoordinates(x,y);
        addActiveTile(coords);
        colorSelectedTiles();
    }

    public boolean checkBounds(int[] c){
        return ( c[0] >= 0 && c[0] < getGridSize()-1 && c[1] >= 0 && c[1] < getGridSize()-1);
    }

    public void addActiveTile(int[] c){
        if (checkBounds(c)){
            selected.add(c);
            selected.add(new int[]{c[0],c[1]+1});
            selected.add(new int[]{c[0]+1,c[1]});
            selected.add(new int[]{c[0]+1,c[1]+1});
        }
    }

    public void placeTiles(){
        if (checkAvailable()) {
            int[] c =  selected.get(0);
            if (checkBounds(c)) {
                Building residence = new Building(new ImageLoader(), c[0], c[1], getCellSize(),getTool());
                getBuildingField().setTile(residence,c[0],c[1]);
                for (int[] c2 : selected) {
                    getTileModel(c2[0],c2[1]).setStatus("UNAVAILABLE");
                }
            }
            clearSelectedTiles();
        }
    }

}
