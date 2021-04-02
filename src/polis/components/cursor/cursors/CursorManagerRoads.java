package polis.components.cursor.cursors;

import polis.components.buildings.buildingtile.tiles.Road;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;
import polis.other.ImageLoader;

import java.util.ArrayList;
import java.util.Map;

public class CursorManagerRoads extends CursorManager {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#D95B6699",
            "AVAILABLE", "#59D98699",
            "UNSELECTED","#FFFFFF00"
    );

    private int[] startOfDrag;

    public CursorManagerRoads(int gridSize, int cellSize, BuildingTileManagerModel buildingField, ArrayList<int[]> selected, CursorTileView[][] tiles){
        super(gridSize,  cellSize, buildingField, selected, tiles);
    }

    public boolean checkBounds(int[] c){
        return ( c[0] >= 0 && c[0] < getGridSize() && c[1] >= 0 && c[1] < getGridSize());
    }

    @Override
    protected void addActiveTile(int[] coords) {
        if (checkBounds(coords)) {
            selected.add(new int[]{coords[0],coords[1]});
        }
    }

    public void setStartDrag(double x, double y){
        startOfDrag = getTileFromCoordinates(x, y);
    }

    @Override
    protected void place() {
        for (int[] c : selected) {
            CursorTileModel t = getTileModel(c[0],c[1]);
            if (!t.getStatus().equals("UNAVAILABLE")) {
                Road r = new Road(new ImageLoader(), c[0], c[1], getCellSize());
                getBuildingField().setTile(r,c[0],c[1]);
                getTileModel(c[0],c[1]).setStatus("UNAVAILABLE");
            }
        }
        clearSelectedTiles();
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setColor(colors.get("UNSELECTED"));
        } selected.clear();
    }

    public void colorSelectedTiles(){
        for (int[] c : selected) {
            CursorTileModel t = getTileModel(c[0],c[1]);
            t.setColor(colors.get(t.getStatus()));
        }
    }

    public void selectDragTiles(int x, int y){
        int t = 1;
        if (startOfDrag[0] > x) {
            t = -1;
        }
        int i=startOfDrag[0];
        while(i != x + t){
            addActiveTile(new int[]{i, startOfDrag[1]});
            i += t;
        }
        t = 1;
        if (startOfDrag[1] > y) {
            t = -1;
        }
        i=startOfDrag[1];
        while(i != y + t){
            addActiveTile(new int[]{x,i});
            i += t;
        }
    }

    public void drag(double x, double y){
        int[] tile = getTileFromCoordinates(x,y);
        clearSelectedTiles();
        if(tile != null && startOfDrag != null){
            selectDragTiles(tile[0],tile[1]);
            colorSelectedTiles();
        }
    }

}
