package polis.datatransferers;

import polis.components.playingfield.buildings.tiles.BuildingTileView;

public class PendingBuildingTileView {

    private final int mode;
    private final BuildingTileView view;

    public PendingBuildingTileView(int mode, BuildingTileView view) {
        this.mode = mode;
        this.view = view;
    }

    public int getMode() {
        return mode;
    }

    public BuildingTileView getView() {
        return view;
    }

}
