package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.Building;

public class Stayer extends Actor {

    public Stayer(int row, int column, String color, String name, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, actorField, name, coords, id, home);
        setColor(color);
    }

    public boolean homeExists(){
        return true;
    }

    @Override
    public void time0() {
        if (homeExists()) {
            transitionToNextFase(nextPhase());
        } else {
            getActorField().removeActor(this);
        }
    }

    @Override
    public void act() { }

    @Override
    public Actor nextPhase() {
        return null;
    }

}
