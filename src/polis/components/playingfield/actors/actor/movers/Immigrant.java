package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Immigrant extends Mover {

    public Immigrant(int row, int column, ActorField actorField) {
        super(row, column, "residence","#d2d2d2", "immigrant", actorField);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        return b.getFunction().equals(getDestination());
    }

    @Override
    public void time0() {
        getActorField().removeActor(this);
        getActorField().getSimulator().slowDown();
    }

}