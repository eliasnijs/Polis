package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.JobSeeker;
import polis.components.playingfield.buildings.tiles.Building;

public class Sleeper extends Stayer {

    private static final String COLOR = "#000000";

    public Sleeper(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, COLOR, "sleeper", actorField, coords, id, home);
    }

    @Override
    public Actor nextPhase() {
        return new JobSeeker(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

}
