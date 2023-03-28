package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.JobSeeker;
import polis.components.playingfield.buildings.tiles.Building;

public class Sleeper extends Stayer {

    public Sleeper(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "sleeper", actorField, coords, id, home);
    }

    @Override
    public Actor nextPhase() {
        return new JobSeeker(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    @Override
    public void time0() {
        if (getResidentId() <= (int) getHome().getCapacity() && getHome().isAlive()) {
            transitionToNextFase(nextPhase());
        } else {
            getHome().addOccupancy(-1);
            getActorField().removeActor(this);
        }
    }

}