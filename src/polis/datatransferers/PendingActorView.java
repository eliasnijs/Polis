package polis.datatransferers;

import polis.components.playingfield.actors.actor.ActorView;

public class PendingActorView {

    private final int mode;
    private final ActorView view;

    public PendingActorView(int mode, ActorView view) {
        this.mode = mode;
        this.view = view;
    }

    public int getMode() {
        return mode;
    }

    public ActorView getView() {
        return view;
    }

}
