package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.Building;

public class Trader extends Stayer {

    private static final String COLOR = "#E6005D";

    private final Building commerce;

    public Trader(int row, int column, ActorField actorField, int[] coords, int id, Building home, Building commerce) {
        super(row, column, COLOR, "worker", actorField, coords, id, home);
        this.commerce = commerce;
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

}
