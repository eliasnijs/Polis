package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Trader;
import polis.components.playingfield.actors.actor.stayers.Worker;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class JobSeeker extends Mover {

    // true -> industryWorker; false -> commerceWorker;
    private boolean next;

    public JobSeeker(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "job","#3A7AFA", "jobseeker",actorField, coords, id, home);
        next = false;
    }

    @Override
    public void time0() {
        super.time0();
        getHome().factorCapacity(Double.parseDouble(
                getActorField().getPropertyLoader().getProperty("engine","factor.job.not.found")));
    }

    @Override
    public Actor nextPhase() {
        getHome().factorCapacity(Double.parseDouble(
                getActorField().getPropertyLoader().getProperty("engine","factor.job.found")));
        return (next)? nextPhaseIndustry() : nextPhaseCommerce();
    }

    public Actor nextPhaseIndustry() {
       return new Worker(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    public Actor nextPhaseCommerce() {
        return new Trader(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        next = b.getName().equals("commerce");
        if ((b.getName().equals("commerce") || b.getName().equals("industry")) && b.getOccupancy() < b.getCapacity()) {
            b.plusOccupancy();
            return true;
        }
        return false;
    }

}
