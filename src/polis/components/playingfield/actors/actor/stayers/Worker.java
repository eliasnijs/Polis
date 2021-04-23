package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.JobSeeker;

public class Worker extends Stayer {

    private static final String COLOR = "#000000";

    public Worker(int row, int column, ActorField actorField) {
        super(row, column, COLOR, "worker", actorField);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField());
    }

}
