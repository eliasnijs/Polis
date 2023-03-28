package polis.components.playingfield.actors.actor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import polis.datakeepers.FieldData;

/**
 * Verantwoordelijk voor de visuele representatie van een actor.
 * **/
public class ActorView extends Circle implements InvalidationListener {

    private final Actor actor;

    public ActorView(Actor actor) {
        this.actor = actor;
        actor.addListener(this);
        setRadius(FieldData.getCellSize()/6.0);
        Update();
    }

    private void Update() {
        int[] t = actor.getPosition();
        setViewOrder(-t[0] - t[1] - 1.1);
        int[] c = actor.getCoords();
        this.setTranslateX(c[0]);
        this.setTranslateY(c[1]);
        this.setFill(Color.web(actor.getColor()));
    }

    public Actor getActor() {
        return actor;
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
