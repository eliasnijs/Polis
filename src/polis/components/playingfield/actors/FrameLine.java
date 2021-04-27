package polis.components.playingfield.actors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import polis.uicomponents.StatsConstructor;

public class FrameLine {

    private Simulator simulator;
    private StatsConstructor statsConstructor;

    private Timeline timeline;

    public FrameLine(Simulator simulator, StatsConstructor statsConstructor){
        this.simulator = simulator;
        this.statsConstructor = statsConstructor;
        initiateTimeline();
    }

    public void initiateTimeline(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(.5), this::nextFrame));
        timeline.play();
    }

    public void nextFrame(ActionEvent actionEvent){
        simulator.nextFrame();
        statsConstructor.Update();
    }

    public void play(boolean selected){
        simulator.setSpawn(selected);
    }

}
