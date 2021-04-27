package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;

public class Residence extends Building {
    public Residence(int row, int column) {
        super(row, column, "residence", "residence", BuildingProperties.RESIDENCE);
    }
}
