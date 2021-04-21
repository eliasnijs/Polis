package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;

public class ResidenceFactory implements BuildingFactory{
    @Override
    public Building createBuilding() {
        return new Residence(0,0);
    }
}
