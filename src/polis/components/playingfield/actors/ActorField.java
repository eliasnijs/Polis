package polis.components.playingfield.actors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.ActorView;
import polis.components.playingfield.actors.actor.movers.Cargo;
import polis.components.playingfield.buildings.BuildingField;
import polis.datatransferers.PendingActorView;
import polis.helpers.PropertyLoader;

import java.util.ArrayList;
import java.util.List;

public class ActorField implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final ArrayList<ActorView> actors;
    private PendingActorView pending;
    private final BuildingField buildingField;
    private final MoverManager moverManager;
    private final PropertyLoader propertyLoader;

    public ActorField(BuildingField buildingField) {
        this.buildingField = buildingField;
        this.moverManager = new MoverManager(this);
        propertyLoader = new PropertyLoader();
        actors = new ArrayList<>();
        pending = null;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(.5), this::act));
        timeline.play();
    }

    public void act(ActionEvent actionEvent) {
        for (ActorView actorView : actors) {
            actorView.getActor().next();
        }
    }

    public PendingActorView getPending() {
        return pending;
    }

    public void newActor(int x, int y) {
        Cargo actor = new Cargo(x, y, this);
        ActorView actorView = new ActorView(actor);
        actors.add(actorView);
        pending = new PendingActorView(0, actorView);
        fireInvalidationEvent();
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
        System.out.println(index);
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

    public PropertyLoader getPropertyLoader() {
        return propertyLoader;
    }

    public BuildingField getBuildingField() {
        return buildingField;
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
