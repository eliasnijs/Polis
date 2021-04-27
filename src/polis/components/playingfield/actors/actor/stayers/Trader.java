package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.Cargo;
import polis.components.playingfield.actors.actor.movers.Shopper;
import polis.components.playingfield.buildings.tiles.Building;

public class Trader extends Stayer {

    private static final String COLOR = "#E6005D";

    private double spawnTime;
    private double time;

    public Trader(int row, int column, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, COLOR, "trader", actorField, coords, id, home);
        spawnTime = Double.parseDouble(
                getActorField().getPropertyLoader().getProperty("engine","steps.per.goods"));
        time = spawnTime;
    }

    @Override
    public Actor nextPhase() {
        return new Shopper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
    }

    public void spawnCargo(){
        Cargo cargo = new Cargo(getPosition()[0],getPosition()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
        getActorField().newActor(cargo);
    }

    @Override
    public void act() {
        super.act();
        time -= 1;
        if (time == 0) {
            spawnCargo();
            time = spawnTime;
        }
    }

}