package polis.components.playingfield.actors;

import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.movers.Immigrant;
import polis.datakeepers.FieldData;
import polis.helpers.PropertyLoader;

import java.util.Random;

/**
 * verantwoordelijk voor het spawnen van immigranten
 * en zegt tegen het actor-veld om voor elke actor de volgende actie te doen.
 * **/
public class Simulator {

    private static final double initialRate = Double.parseDouble(PropertyLoader.getProperty("engine", "region.initial.rate"));
    private static final double slowestRate =  Double.parseDouble(PropertyLoader.getProperty("engine", "region.slowest.rate"));
    private static final double recoveryFactor = Double.parseDouble(PropertyLoader.getProperty("engine", "region.factor.recovery"));
    private static final double slowDownFactor = Double.parseDouble(PropertyLoader.getProperty("engine", "region.factor.slow.down"));

    private final ActorField actorField;
    private double framesUntilNextSpawn;
    private double tempo;
    private final Random RG;

    public Simulator(ActorField actorField) {
        this.actorField = actorField;
        RG = new Random();
        tempo = initialRate;
        framesUntilNextSpawn();
    }

    private void spawn() {
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

    private void recovery() {
        tempo = Math.max(tempo * recoveryFactor, initialRate);
    }

    public void slowDown() {
        tempo = Math.min(tempo * slowDownFactor, slowestRate);
    }

    private void framesUntilNextSpawn() {
        framesUntilNextSpawn = RG.nextInt((int) tempo);
    }

    public void reset() {
        tempo = initialRate;
        framesUntilNextSpawn();
    }

}
