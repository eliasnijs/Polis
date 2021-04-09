package polis.components.cursor.cursors;

import javafx.scene.paint.Color;
import polis.components.buildings.BuildingFieldModel;
import polis.components.buildings.buildingtile.BuildingTileView;
import polis.components.buildings.buildingtile.tiles.Road;
import polis.components.cursor.CursorFieldModel;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.other.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CursorManagerRoads extends CursorManager {

    private static final Map<Boolean,String> colors = Map.of(
            false, "#D95B6699",
            true, "#59D98699"
    );

    private int[] startOfDrag;
    private final ArrayList<int[]> pos;
    private final ImageLoader imageLoader;

    public CursorManagerRoads(ImageLoader imageLoader , int g, int c, BuildingFieldModel bf, ArrayList<int[]> s, CursorFieldModel t){
        super(g,  c, bf, s, t);
        pos = new ArrayList<>();
        Collections.addAll(pos,new int[]{-1,0},new int[]{0,1},new int[]{1,0},new int[]{0,-1});
        this.imageLoader = imageLoader;
    }

    public boolean checkBounds(int[] c){
        return ( c[0] >= 0 && c[0] < getGridSize() && c[1] >= 0 && c[1] < getGridSize());
    }

    @Override
    protected void addActiveTile(int[] c) {
        if (checkBounds(c)) {
            selected.add(new int[]{c[0],c[1]});
        }
    }

    @Override
    public void setStartDrag(double x, double y){
        startOfDrag = getTileFromCoordinates(x, y);
    }

    @Override
    public void place() {
        placeTiles();
        clearSelectedTiles();
    }

    public void clearSelectedTiles(){
        for (int[] c : selected) {
               getCursorFieldModel().deleteTile(c[0],c[1]);
        } selected.clear();
    }

    public void colorSelectedTiles(){
        for (int[] c : selected) {
            CursorTileModel cursorTile = new CursorTileModel(c[0], c[1], getCellSize());
            getCursorFieldModel().setTile(cursorTile, c[0], c[1], 1);
            cursorTile.setColor(colors.get(isAvailable(c)));
        }
    }

    public void selectDragTiles(int x, int y){
        int t = (startOfDrag[0] > x)? -1 : 1;
        int i = startOfDrag[0];
        while(i != x+t){
            addActiveTile(new int[]{i, startOfDrag[1]});
            i += t;
        }
        t = (startOfDrag[1] > y)? -1 : 1;
        i = startOfDrag[1];
        while(i != y+t){
            addActiveTile(new int[]{x,i});
            i += t;
        }
    }

    public void drag(double x, double y){
        clearSelectedTiles();
        int[] tile = getTileFromCoordinates(x,y);
        if (tile != null && startOfDrag != null){
            selectDragTiles(tile[0],tile[1]);
            colorSelectedTiles();
        }
    }

    public void placeTiles() {
        for (int[] c : selected) {
            if (isAvailable(c)) {
                boolean[] adjacent = checkNeighbours(c[0],c[1]);
                Road r = new Road(imageLoader, c[0], c[1], getCellSize(), getBuildingField(),adjacent);
                getBuildingField().setTile(r,c[0],c[1]);
                for (int i=0; i<pos.size(); i++) {
                    int[] s = pos.get(i);
                    if (adjacent[i]) {
                        boolean[] adj = checkNeighbours(c[0] + s[0], c[1] + s[1]);
                        getBuildingField().getTiles()[c[0] + s[0]][c[1] + s[1]].getModel().setNeighbours(adj);
                    }
                }
            }
        }
    }

    public boolean[] checkNeighbours(int row, int column){
        boolean[] neighbours = new boolean[]{false,false,false,false};
        for (int i=0; i<4; i++) {
            int[] c = pos.get(i);
            if (checkBounds(new int[]{row+c[0],column+c[1]})) {
                BuildingTileView t = getBuildingField().getTiles()[row+c[0]][column+c[1]];
                if (t != null){
                    if (t.getModel().getName().equals("road")) {
                        neighbours[i] = true;
                    }
                }
            }
        }
        return neighbours;
    }

}

























