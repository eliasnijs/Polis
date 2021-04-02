package polis.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    private boolean muted;

    public MusicPlayer(String musicFile) {
        muted = false;
        Media startMusic = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(startMusic);
        mediaPlayer.play();
    }

    public void changeMusic(String musicFile){
        Media newMusic = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(newMusic);
        mediaPlayer.setMute(muted);
        mediaPlayer.play();
    }

    public void switchMute(){
        muted = !muted;
        mediaPlayer.setMute(muted);
    }
    public boolean getMuted(){
        return muted;
    }

    public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }
    public double getVolume(){
        return  mediaPlayer.getVolume();
    }
}