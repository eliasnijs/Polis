package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.Building;

public class Worker extends Stayer {

    private static final String COLOR = "#E6005D";

    public Worker(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, COLOR, "worker", actorField, coords, id, home);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

}
