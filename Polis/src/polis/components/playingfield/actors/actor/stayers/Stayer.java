package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.Building;

public class Stayer extends Actor {

    public Stayer(int row, int column, String name, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, actorField, name, coords, id, home);
        setColor("00000000");
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
