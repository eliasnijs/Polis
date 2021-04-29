package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;

public class Customer extends Stayer {

    private final Commerce shop;

    public Customer(int row, int column, ActorField actorField, int[] coords, int id, Building home, Commerce shop) {
        super(row, column, "customer", actorField, coords, id, home);
        this.shop = shop;
    }

    @Override
    public Actor nextPhase() {
        shop.addOccupancy(-1);
        shop.addGoods(-1);
        return new Sleeper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

}