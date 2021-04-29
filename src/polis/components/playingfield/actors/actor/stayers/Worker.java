package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.Cargo;
import polis.components.playingfield.actors.actor.movers.Shopper;
import polis.components.playingfield.buildings.tiles.Building;
import polis.helpers.PropertyLoader;

public class Worker extends Stayer {

    private final double stepsPerGoods;
    private final Building factory;

    public Worker(int row, int column, ActorField actorField, int[] coords, int id, Building home, Building factory) {
        super(row, column, "trader", actorField, coords, id, home);
        this.factory = factory;
        stepsPerGoods = Double.parseDouble(
                PropertyLoader.getProperty("engine","steps.per.goods"));
    }

    @Override
    public Actor nextPhase() {
        factory.addOccupancy(-1);
        return new Shopper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    private void spawnCargo(){
        Cargo cargo = new Cargo(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), factory);
        getActorField().newActor(cargo);
    }

    @Override
    public void act() {
        super.act();
        if (getAge()%stepsPerGoods == 0) {
            spawnCargo();
        }
    }

}
