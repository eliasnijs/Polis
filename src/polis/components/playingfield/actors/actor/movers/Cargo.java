package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Cargo extends Mover {

    public Cargo(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "commerce","#FA944B", "goods", actorField, coords, id, home);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        return b.getName().equals(getDestination());
    }

    @Override
    public void time0() {
        getActorField().removeActor(this);
    }

}
