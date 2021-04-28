package polis.components.playingfield.actors;

import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.Immigrant;
import polis.datakeepers.FieldData;
import polis.helpers.PropertyLoader;

import java.util.Random;

public class Simulator {

    private final ActorField actorField;

    private final double initialRate;
    private final double slowestRate;
    private final double recoveryFactor;
    private final double slowDownFactor;

    private double framesUntilNextSpawn;
    private double tempo;

    private final Random RG;


    public Simulator(ActorField actorField) {
        this.actorField = actorField;
        initialRate = Double.parseDouble(PropertyLoader.getProperty("engine", "region.initial.rate"));
        slowestRate = Double.parseDouble(PropertyLoader.getProperty("engine", "region.slowest.rate"));
        recoveryFactor = Double.parseDouble(PropertyLoader.getProperty("engine", "region.factor.recovery"));
        slowDownFactor = Double.parseDouble(PropertyLoader.getProperty("engine", "region.factor.slow.down"));

        RG = new Random();
        tempo = initialRate;
        framesUntilNextSpawn();
    }

    public void spawn() {
        Actor actor = new Immigrant(0, FieldData.getGridSize() / 2 - 1, actorField);
        actorField.newActor(actor);
    }

    public void nextFrame() {
        actorField.act();
        framesUntilNextSpawn -= 1;
        recovery();
        if (framesUntilNextSpawn < 0) {
            spawn();
            framesUntilNextSpawn();
        }
    }

    public void recovery() { tempo = Math.max(tempo * recoveryFactor, initialRate); }

    public void slowDown() {
        tempo = Math.min(tempo * slowDownFactor, slowestRate);
    }

    public void framesUntilNextSpawn() { framesUntilNextSpawn = RG.nextInt((int) tempo); }

}
