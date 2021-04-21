package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;

public class IndustryFactory implements BuildingFactory{
    @Override
    public Building createBuilding() {
        return new Industry(0,0);
    }
}
