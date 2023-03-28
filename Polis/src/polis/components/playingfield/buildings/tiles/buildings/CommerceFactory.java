package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;

public class CommerceFactory implements BuildingFactory{
    @Override
    public Building createBuilding() {
        return new Commerce(0,0);
    }
}
