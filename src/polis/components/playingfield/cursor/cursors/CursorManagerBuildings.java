package polis.components.playingfield.cursor.cursors;

import polis.components.playingfield.buildings.buildingtile.tiles.Residence;
import polis.components.playingfield.cursor.CursorManager;
import polis.components.playingfield.cursor.cursortile.CursorTileView;
import polis.components.playingfield.buildings.BuildingTileManagerModel;
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
        } return true;
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

    public void addActiveTile(int[] c){
        selected.add(c);
        selected.add(new int[]{c[0],c[1]+1});
        selected.add(new int[]{c[0]+1,c[1]});
        selected.add(new int[]{c[0]+1,c[1]+1});
    }

    public void placeTiles(){
        if (checkAvailable()) {
            int[] c =  selected.get(0);
            Residence residence = new Residence(new ImageLoader(), c[0], c[1], getCellSize());
            getBuildingField().setTile(residence,c[0],c[1]);
            for (int[] s : selected) {
                getTileModel(s[0],s[1]).setStatus("UNAVAILABLE");
            }
        }
    }

}
