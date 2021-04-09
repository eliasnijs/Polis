package polis.components.cursor.cursors;

import polis.components.buildings.BuildingFieldModel;
import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.tiles.Building;
import polis.components.cursor.CursorFieldModel;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorManagerBuildings extends CursorManager {

    private static final Map<Boolean,String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

    public CursorManagerBuildings(int gridSize, int cellSize, BuildingFieldModel buildingField, ArrayList<int[]> selected, CursorFieldModel tiles){
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
            getCursorFieldModel().deleteTile(c[0],c[1]);
        }
        selected.clear();
    }

    public boolean checkAvailable(){
        for (int[] c : selected) {
            if (!isAvailable(c)) {
                return false;
            }
        }
        return !selected.isEmpty();
    }

    public void colorSelectedTiles(){
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1], getCellSize());
            getCursorFieldModel().setTile(cursorTile, c[0], c[1], 1);
            cursorTile.setColor(colors.get(isAvailable(c)));
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

    public void placeTiles() {
        if (checkAvailable()) {
            int[] c =  selected.get(0);
            if (checkBounds(c)) {
                BuildingTileModel b = new Building(new ImageLoader(),c[0],c[1],getCellSize(),getTool());
                getBuildingField().setTile(b,b.getRow(),b.getColumn());
            }
        }
        clearSelectedTiles();
    }

}
