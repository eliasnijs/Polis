package polis.components.playingfield.actors;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.ActorView;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.List;

public class ActorField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private ArrayList<ActorView> actors;

    public ActorField() {
        actors = new ArrayList<>();
    }

    public void newActor(int x, int y) {
        int[] c = GridCoordsConverter.coordsToGrid(x, y);
        Actor actor = new Actor(c[0], c[1]);
        ActorView actorView = new ActorView(actor);
        actors.add(actorView);
        fireInvalidationEvent();
    }

    public ArrayList<ActorView> getActors() {
        return actors;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
