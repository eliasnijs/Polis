package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;

public class Industry extends Building {
    public Industry(int row, int column) {
        super(row, column, "industry", BuildingProperties.INDUSTRY);
    }
}
