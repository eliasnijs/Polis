package polis.components.playingfield.actors.actor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.helpers.GridCoordsConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final ActorField actorField;

    private String color;
    private int[] position;
    private int age;
    private int residentId;
    private int[] baseCoords;
    private Building home;

    public Actor(int row, int column, ActorField actorfield, String name, int[] coords, int id, Building home) {
        position = new int[]{row, column};
        color = "#ffffff";
        this.baseCoords = coords;
        this.residentId = id;
        this.actorField = actorfield;
        this.age = Integer.parseInt(actorfield.getPropertyLoader().getProperty("engine", name + ".age"));
        this.home = home;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        fireInvalidationEvent();
    }

    public int[] getCoords() {
        return GridCoordsConverter.gridToCoords(position);
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
        fireInvalidationEvent();
    }

    public ActorField getActorField() {
        return actorField;
    }

    public void next() {
        act();
        age--;
        if (age == 0) {
            time0();
        }
    }

    public Building getHome() {
        return home;
    }

    public void setHome(Building home) {
        this.home = home;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int[] getBaseCoords() {
        return baseCoords;
    }

    public void setBaseCoords(int[] baseCoords) {
        this.baseCoords = baseCoords;
    }

    public void transitionToNextFase(Actor actor) {
        actorField.nextActorPhase(this, actor);
    }

    public abstract void time0();

    public abstract void act();

    public abstract Actor nextPhase();

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
