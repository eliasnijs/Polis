package polis.datatransferers;

import polis.components.playingfield.actors.actor.ActorView;

/**
 * Object om data door te geven aan de visuele representatie van het actoren-veld.
 * Dit bevat een actor en een aanduiding of de visuele component deze actor moet toevoegen of verwijderen.
 * **/
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
