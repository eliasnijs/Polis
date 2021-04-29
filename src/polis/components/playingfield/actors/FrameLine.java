package polis.components.playingfield.actors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import polis.uicomponents.statistics.StatsController;


/**
 * Verantwoordelijk voor de tijdlijn.
 * **/
public class FrameLine {

    private final Simulator simulator;
    private final StatsController statsConstructor;

    private Timeline timeline;

    public FrameLine(Simulator simulator, StatsController statsConstructor){
        this.simulator = simulator;
        this.statsConstructor = statsConstructor;
        initiateTimeline();
    }

    private void initiateTimeline(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(.25), this::nextFrame));
    }

    private void nextFrame(ActionEvent actionEvent){
        simulator.nextFrame();
        statsConstructor.Update();
    }

    public void play(boolean selected){
        timeline.stop();
        if (selected) {
            timeline.play();
        }
    }

    public void setSpeed(int amount){
        timeline.setRate(amount/100.0);
    }

}
