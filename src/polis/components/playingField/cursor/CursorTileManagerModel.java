package polis.components.playingField.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CursorTileManagerModel implements Observable {

    private static final Map<String,String> colors = Map.of(
            "UNAVAILABLE", "#D95B6699",
            "AVAILABLE", "#59D98699",
            "UNSELECTED","#FFFFFF00"
    );

    private static final int GRID_SIZE = 32;
    private static final int CELL_SIZE = 64;

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final CursorTileView[][] tiles;
    private final ArrayList<CursorTileModel> activeTiles;

    private int size = 1;
    private CursorTileModel startOfDrag;

    public CursorTileManagerModel(){
        activeTiles = new ArrayList<>();
        tiles = new CursorTileView[GRID_SIZE][GRID_SIZE];
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                tiles[i][j] = new CursorTileView(new CursorTileModel(colors.get("UNSELECTED"), i, j, 1, CELL_SIZE));
            }
        }
    }

    // Getters
    public static int getGridSize() {
        return GRID_SIZE;
    }

    public static int getCellSize() {
        return CELL_SIZE;
    }

    public CursorTileModel getTileModel(int row, int column){
        return tiles[row][column].getModel();
    }

    public CursorTileView[][] getTiles() {
        return tiles;
    }

    public ArrayList<CursorTileModel> getActiveTiles(){
        return activeTiles;
    }

    public CursorTileModel getTileFromCoordinates(double x, double y){
        int column = (int) ((x/CELL_SIZE)/2 + y/CELL_SIZE - size + 0.5);
        int row = (int) ((-x/CELL_SIZE)/2 + y/CELL_SIZE + 0.5);
        if(row >= 0 && column >= 0 && row < GRID_SIZE && column < GRID_SIZE){
            return this.getTileModel(row , column);
        }
        return null;
    }

    // Setters
    public void setTileActive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get("AVAILABLE"));
        }
    }

    public void setTileInactive(CursorTileModel t){
        if(t != null ){
            t.setColor(colors.get("UNSELECTED"));
        }
    }

    public void switchSize(){
        size = size+1;
        if(this.size == 3){
            this.size = 1;
        }
    }

    public void clearActiveTiles(){
        if (activeTiles.size() > 0) {
            for (CursorTileModel c : activeTiles) {
                setTileInactive(c);
            }
            activeTiles.clear();
        }
    }

    public void colorActiveTiles(){
        for (CursorTileModel c : activeTiles) {
            setTileActive(c);
        }
    }

    // Functionality
    public void hoover(double x, double y) {
        clearActiveTiles();
        CursorTileModel tile = getTileFromCoordinates(x, y);
        if(tile == null){
            return;
        }
        addActiveTile(tile);
        colorActiveTiles();
    }

    public void setStartDrag(double x, double y){
        startOfDrag = getTileFromCoordinates(x, y);
    }

    public void drag(double x, double y){
        CursorTileModel tile = getTileFromCoordinates(x,y);
        clearActiveTiles();
        if(tile == null || startOfDrag == null){
            return;
        }
        // Add active tiles
        int t = 1;
        if (startOfDrag.getRow() > tile.getRow()) {
            t = -1;
        }
        int i=startOfDrag.getRow();
        while(i != tile.getRow() + t){
            addActiveTile(getTileModel(i, startOfDrag.getColumn()));
            i += t;
        }
        t = 1;
        if (startOfDrag.getColumn() > tile.getColumn()) {
            t = -1;
        }
        i=startOfDrag.getColumn();
        while(i != tile.getColumn() + t){
            addActiveTile(getTileModel(tile.getRow(), i));
            i += t;
        }
        colorActiveTiles();
    }

    public void addActiveTile(CursorTileModel tile){
        if (size == 2) {
            if(tile.getRow()+1 >= 0 && tile.getRow()+1 < GRID_SIZE && tile.getColumn()+1 >= 0 && tile.getColumn()+1 < GRID_SIZE){
                activeTiles.add(tile);
                activeTiles.add(getTileModel(tile.getRow(), tile.getColumn()+1));
                activeTiles.add(getTileModel(tile.getRow()+1, tile.getColumn()));
                activeTiles.add(getTileModel(tile.getRow()+1, tile.getColumn()+1));
            }
        } else {
            activeTiles.add(tile);
        }
    }

    public void mousePressed(){
        clearActiveTiles();
    }


    // External communication
    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    private void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
}
