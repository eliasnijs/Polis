package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Customer;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Shopper extends Mover {

    public Shopper(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "commerce","#FA4F4C", "shopper", actorField, coords, id, home);
    }

    @Override
    public void time0() {
        super.time0();
        getHome().factorCapacity(Double.parseDouble(
                getActorField().getPropertyLoader().getProperty("engine","factor.shop.not.found")));
    }

    @Override
    public Actor nextPhase() {
        getHome().factorCapacity(Double.parseDouble(
                getActorField().getPropertyLoader().getProperty("engine","factor.shop.found")));
        return new Customer(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        return b.getName().equals(getDestination()) && b.getOccupancy() < b.getCapacity();
    }

}
