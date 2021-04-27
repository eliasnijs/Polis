package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Immigrant extends Mover {

    public Immigrant(int row, int column, ActorField actorField) {
        super(row, column, "residence","#d2d2d2", "immigrant", actorField, new int[]{-1,-1}, 0, null);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        if (b.getFunction().equals(getDestination()) && b.getOccupancy() < b.getCapacity()) {
            b.plusOccupancy();
            setBaseCoords(getPosition());
            setResidentId(b.getOccupancy() + 1);
            setHome((Building) b);
            return true;
        }
        return false;
    }

    @Override
    public void time0() {
        getActorField().removeActor(this);
        getActorField().getSimulator().slowDown();
    }

}