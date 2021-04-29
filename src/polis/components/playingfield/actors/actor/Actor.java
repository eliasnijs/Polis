package polis.components.playingfield.actors.actor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.helpers.GridCoordsConverter;
import polis.helpers.PropertyLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * KLasse die algemene data van een actor bijhoudt.
 * **/
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
        this.age = Integer.parseInt(PropertyLoader.getProperty("engine", name + ".age"));
        this.home = home;
    }

    protected int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
        fireInvalidationEvent();
    }

    public int[] getCoords() {
        return GridCoordsConverter.gridToCoords(position);
    }

    public int[] getPosition() {
        return position;
    }

    protected void setPosition(int[] position) {
        this.position = position;
        fireInvalidationEvent();
    }

    protected ActorField getActorField() {
        return actorField;
    }

    public void next() {
        act();
        age--;
        if (age == 0) {
            time0();
        }
    }

    protected Building getHome() {
        return home;
    }

    protected void setHome(Building home) {
        this.home = home;
    }

    protected int getResidentId() {
        return residentId;
    }

    protected void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    protected int[] getBaseCoords() {
        return baseCoords;
    }

    protected void setBaseCoords(int[] baseCoords) {
        this.baseCoords = baseCoords;
    }

    protected void transitionToNextFase(Actor actor) {
        actorField.nextActorPhase(this, actor);
    }

    protected abstract void time0();

    protected abstract void act();

    public abstract Actor nextPhase();

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
