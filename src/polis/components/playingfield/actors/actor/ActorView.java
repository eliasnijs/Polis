package polis.components.playingfield.actors.actor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ActorView extends Circle implements InvalidationListener {

    private Actor actor;

    public ActorView(Actor actor) {
        this.actor = actor;
        actor.addListener(this);
        setRadius(10);
        Update();
    }

    public void Update() {
        int[] c = actor.getCoords();
        this.setTranslateX(c[0]);
        this.setTranslateY(c[1]);
        setViewOrder(-c[0] - c[1] - 1.0);
        this.setFill(Color.web(actor.getColor()));
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
