package polis.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MusicPlayer {

    private static final String soundtrackLocation = "resources/polis/music/soundtracks/";
    private final ArrayList<File> tracks;

    private MediaPlayer mediaPlayer;
    private boolean muted;

    public MusicPlayer() {

        tracks = new ArrayList<>();

        File dir = new File(soundtrackLocation);
        File[] songs = dir.listFiles();
        if(songs != null){
            tracks.addAll(Arrays.asList(songs));

            muted = false;
            Media s = new Media(selectRandomMusic());
            changeMusic(s);
        }
    }

    public void changeMusic(Media musicFile){
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setMute(muted);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::newMusic);
    }

    public void newMusic(){
        Media s = new Media(selectRandomMusic());
        changeMusic(s);
    }

    public void switchMute(){
        muted = !muted;
        mediaPlayer.setMute(muted);
    }

    public boolean getMuted(){
        return muted;
    }

    private String selectRandomMusic(){
        Collections.shuffle(tracks);
        return tracks.get(0).toURI().toString();
    }

}