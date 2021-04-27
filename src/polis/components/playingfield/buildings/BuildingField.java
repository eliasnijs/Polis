package polis.components.playingfield.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.buildings.tiles.*;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingBuildingTileView;
import polis.helpers.PropertyLoader;
import polis.other.Noise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BuildingField implements Observable {

    private final PropertyLoader propertyLoader;
    private final List<InvalidationListener> listenerList = new ArrayList<>();
    private final BuildingTileView[][] tiles;
    private PendingBuildingTileView pending;

    public BuildingField() {
        propertyLoader = new PropertyLoader();
        tiles = new BuildingTileView[FieldData.getGridSize()][FieldData.getGridSize()];

    }

    public BuildingTileView[][] getTiles() {
        return tiles;
    }

    public PendingBuildingTileView getPendingView() {
        return pending;
    }

    public void setTile(BuildingTileModel tile) {
        BuildingTileView tileView = new BuildingTileView(tile);
        int row = tile.getRow();
        int column = tile.getColumn();
        tiles[row][column] = tileView;
        if (tile.getSize() == 2) {
            tiles[row][column + 1] = tileView;
            tiles[row + 1][column] = tileView;
            tiles[row + 1][column + 1] = tileView;
        }
        tileView.setViewOrder(-row - column - 2 * (tile.getSize() - 1) - 1.0);
        pending = new PendingBuildingTileView(0, tileView);
        fireInvalidationEvent();
        pending = null;
    }

    public void deleteTile(int row, int column) {
        pending = new PendingBuildingTileView(1, tiles[row][column]);
        fireInvalidationEvent();
        pending = null;
    }

    public HashSet<Building> getBuildingTilesArray(){
        HashSet<Building> tilesArray = new HashSet<>();
        for (BuildingTileView[] row : tiles) {
            for (BuildingTileView tile : row) {
                if (tile != null) {
                    if (tile.getModel().getSize() == 2) {
                        tilesArray.add((Building) tile.getModel());
                    }
                }
            }
        }
        return tilesArray;
    }

    public void setStartupTrees(float roughness) {
        Noise noise = new Noise(null, roughness, FieldData.getGridSize(), FieldData.getGridSize());
        ArrayList<int[]> locations = noise.getLocations();
        locations.removeIf(c -> c[1] == FieldData.getGridSize() / 2 - 1 && c[0] < FieldData.getGridSize() / 2);
        for (int[] c : locations) {
            setTile(new Tree(c[0], c[1]));
        }
    }

    public void setStartupRoads() {
        int t = FieldData.getGridSize() / 2 - 1;
        for (int i = 0; i < FieldData.getGridSize() / 2 - 1; i += 1) {
            setTile(new Road(i, t, new boolean[]{true, false, true, false}, false));
        }
        setTile(new Road(t, t, new boolean[]{true, false, false, false}, false));
    }

    public PropertyLoader getPropertyLoader() {
        return propertyLoader;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}