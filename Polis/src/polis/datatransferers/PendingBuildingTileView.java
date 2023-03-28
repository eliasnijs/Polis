package polis.datatransferers;

import polis.components.playingfield.buildings.tiles.BuildingTileView;

/**
 * Object om data door te geven aan de visuele representatie van het gebouwen-veld.
 * Dit bevat een gebouw en een aanduiding of de visuele component dit gebouw moet toevoegen of verwijderen.
 * **/
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
