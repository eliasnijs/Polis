package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.Shopper;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;

public class Trader extends Stayer {

    private final Commerce commerce;

    public Trader(int row, int column, ActorField actorField, int[] coords, int id, Building home, Commerce commerce) {
        super(row, column, "worker", actorField, coords, id, home);
        this.commerce = commerce;
    }

    @Override
    public Actor nextPhase() {
        commerce.addJobs(-1);
        return new Shopper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

}
