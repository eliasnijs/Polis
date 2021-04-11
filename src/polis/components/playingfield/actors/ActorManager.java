package polis.components.playingfield.actors;

public class ActorManager {

    private ActorField actorField;

    public ActorManager(){
        actorField = new ActorField();
    }

    public ActorField getActorField() {
        return actorField;
    }


}
