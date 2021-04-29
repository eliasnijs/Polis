package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Trader;
import polis.components.playingfield.actors.actor.stayers.Worker;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;
import polis.components.playingfield.buildings.tiles.buildings.Industry;
import polis.helpers.PropertyLoader;

public class JobSeeker extends Mover {

    // true -> industryWorker; false -> commerceWorker;
    private boolean next;
    private BuildingTileModel building;

    public JobSeeker(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, "job","#FA4F4C", "jobseeker",actorField, coords, id, home);
        next = false;
    }

    @Override
    public void time0() {
        super.time0();
        getHome().factorCapacity(Double.parseDouble(
                PropertyLoader.getProperty("engine","factor.job.not.found")));
    }

    @Override
    public Actor nextPhase() {
        getHome().factorCapacity(Double.parseDouble(
                PropertyLoader.getProperty("engine","factor.job.found")));
        return (next)? nextPhaseCommerce() : nextPhaseIndustry();
    }

    private Actor nextPhaseIndustry() {
        Industry industry = (Industry) building;
        industry.addOccupancy(1);
        return new Worker(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome(), industry);
    }

    private Actor nextPhaseCommerce() {
        Commerce shop = (Commerce) building;
        shop.addJobs(1);
        return new Trader(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome(), shop);
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        building = b;
        if (b.getName().equals("commerce")) {
            Commerce c = (Commerce) b;
            next = c.getJobs() < Math.ceil(c.getJobsCapacity());
            if (!next) {c.badTrade();}
            return next;
        }
        if (b.getName().equals("industry")) {
            return b.getOccupancy() < Math.floor(b.getCapacity());
        }
        return false;
    }

}
