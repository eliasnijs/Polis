package polis.components.playingfield.actors;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.ActorView;
import polis.components.playingfield.buildings.BuildingField;
import polis.datatransferers.PendingActorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Verantwoordelijk voor het bijhouden van alle actors.
 * **/
public class ActorField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final ArrayList<ActorView> actors;
    private PendingActorView pending;
    private final BuildingField buildingField;
    private final MoverManager moverManager;
    private final Simulator simulator;

    public ActorField(BuildingField buildingField) {
        this.buildingField = buildingField;
        this.moverManager = new MoverManager(this);
        simulator = new Simulator(this);
        actors = new ArrayList<>();
        pending = null;
    }

    public void act() {
        ArrayList<ActorView> actorsTemp = new ArrayList<>(actors);
        for (ActorView actorView : actorsTemp) {
            actorView.getActor().next();
        }
    }

    public PendingActorView getPending() {
        return pending;
    }

    public void newActor(Actor actor) {
        ActorView actorView = new ActorView(actor);
        actors.add(actorView);
        pending = new PendingActorView(0, actorView);
        fireInvalidationEvent();
    }

    public void removeActor(Actor actor) {
        boolean found = false;
        int index = 0;
        while (!found && index < actors.size()) {
            if (actors.get(index).getActor() == actor) {
                pending = new PendingActorView(1, actors.get(index));
                actors.remove(index);
                found = true;
            }
            index += 1;
        }
        fireInvalidationEvent();
    }

    public void nextActorPhase(Actor previous, Actor next) {
        newActor(next);
        removeActor(previous);
    }

    public MoverManager getMoverManager() {
        return moverManager;
    }

    public BuildingField getBuildingField() {
        return buildingField;
    }

    public ArrayList<ActorView> getActors() {
        return actors;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
