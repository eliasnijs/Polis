package polis.components.playingfield.actors.actor.stayers;

import polis.components.playingfield.actors.actor.Actor;

public class Stayer extends Actor {

    private int time;

    public Stayer(int row, int column, String color , int time) {
        super(row, column);
        this.time = time;
        setColor(color);
    }

    @Override
    public void act() {
        time -= 1;
        time0();
    }

    public void time0(){
        if (time == 0) {
            System.out.println("stayer is done");
        }
    }

}
