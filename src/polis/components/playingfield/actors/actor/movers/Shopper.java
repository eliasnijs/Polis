package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Customer;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;
import polis.helpers.PropertyLoader;

public class Shopper extends Mover {

    private Commerce shop;

    public Shopper(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "commerce","#3A7AFA", "shopper", actorField, coords, id, home);
    }

    @Override
    public void time0() {
        super.time0();
        getHome().factorCapacity(Double.parseDouble(
                PropertyLoader.getProperty("engine","factor.shop.not.found")));
    }

    @Override
    public Actor nextPhase() {
        getHome().factorCapacity(Double.parseDouble(
                PropertyLoader.getProperty("engine","factor.shop.found")));
        shop.addOccupancy(1);
        return new Customer(
                getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome(), shop);
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        if (b.getName().equals(getDestination())) {
            shop = (Commerce) b;
            return Math.floor(shop.getGoods()) > Math.floor(shop.getOccupancy()) && Math.floor(shop.getOccupancy()) < Math.floor(shop.getJobs()) * 3;
        }
        return false;
    }

}
