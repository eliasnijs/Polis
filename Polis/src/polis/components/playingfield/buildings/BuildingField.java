package polis.components.playingfield.buildings;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.Manager;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.components.playingfield.buildings.tiles.Road;
import polis.components.playingfield.buildings.tiles.Tree;
import polis.datakeepers.FieldData;
import polis.datatransferers.PendingBuildingTileView;
import polis.helpers.RoadChecker;
import polis.other.Noise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasse die alle gebouwen bijhoudt en het opvragen en bijwerken van de gebouwen faciliteert.
 * **/
public class BuildingField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();
    private final Manager manager;
    private final BuildingTileView[][] tiles;
    private PendingBuildingTileView pending;

    public BuildingField(Manager manager) {
        this.manager = manager;
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
        manager.getStatsConstructor().Update();
    }

    public void deleteTile(int r, int c) {
        BuildingTileView v = tiles[r][c];
        pending = new PendingBuildingTileView(1, tiles[r][c]);
        fireInvalidationEvent();
        BuildingTileModel b = null;
        for (int i = 0; i < FieldData.getGridSize(); i++) {
            for (int j = 0; j < FieldData.getGridSize(); j++) {
                if (tiles[i][j] == v) {
                    b = tiles[i][j].getModel();
                    tiles[i][j].getModel().setAlive(false);
                    tiles[i][j] = null;
                }
            }
        }
        if (manager.getStatsConstructor().getBuilding() == b) {
            manager.getStatsConstructor().importBuilding(null);
        }
        manager.getStatsConstructor().Update();
        updateSurroundingRoads(new int[]{r,c});
    }

    public void updateSurroundingRoads(int[] c){
        ArrayList<int[]> pos = new ArrayList<>();
        Collections.addAll(pos, new int[]{-1 , 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1});
        boolean[] adjacent = RoadChecker.checkRoadNeighbours(this,c[0],c[1]);
        for (int i = 0; i < pos.size(); i++) {
            int[] s = pos.get(i);
            if (adjacent[i]) {
                boolean[] adj = RoadChecker.checkRoadNeighbours(this, c[0] + s[0], c[1] + s[1]);
                this.getTiles()[c[0] + s[0]][c[1] + s[1]].getModel().setNeighbours(adj);
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