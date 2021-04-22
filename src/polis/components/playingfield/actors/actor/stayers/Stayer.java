package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;

public class Stayer extends Actor {

    public Stayer(int row, int column, String color, String name, ActorField actorField) {
        super(row, column, actorField, name);
        setColor(color);
    }

    @Override
    public void time0() {
        transitionToNextFase(nextPhase());
    }

    @Override
    public void act() { }

    @Override
    public Actor nextPhase() {
        return null;
    }

}
