package polis.components.playingfield.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.components.playingfield.buildings.tiles.Road;
import polis.components.playingfield.buildings.tiles.Tree;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingBuildingTileView;
import polis.other.Noise;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die alle gebouwen bijhoudt en het opvragen en bijwerken van de gebouwen faciliteert.
 * **/
public class BuildingField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();
    private final BuildingTileView[][] tiles;
    private PendingBuildingTileView pending;

    public BuildingField() {
        tiles = new BuildingTileView[FieldData.getGridSize()][FieldData.getGridSize()];
    }

    public BuildingTileView[][] getTiles() {
        return tiles;
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
    }

    public void deleteTile(int r, int c) {
        BuildingTileView b = tiles[r][c];
        pending = new PendingBuildingTileView(1, tiles[r][c]);
        fireInvalidationEvent();
        for (int i = 0; i < FieldData.getGridSize(); i++) {
            for (int j = 0; j < FieldData.getGridSize(); j++) {
                if (tiles[i][j] == b) {
                    tiles[i][j] = null;
                }
            }
        }
    }

    public PendingBuildingTileView getPendingView() {
        return pending;
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

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}