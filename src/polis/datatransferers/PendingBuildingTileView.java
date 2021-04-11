package polis.datatransferers;

import polis.components.playingfield.buildings.BuildingTileView;

public class PendingBuildingTileView {

    private int mode;
    private BuildingTileView view;

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
