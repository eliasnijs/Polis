package polis.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class MusicPlayer {

    private String soundtrackLocation = "polis/music/soundtracks/";
    private final ArrayList<File> tracks;

    private MediaPlayer mediaPlayer;
    private boolean muted;

    public MusicPlayer() throws URISyntaxException {


        tracks = new ArrayList<>();

        File dir = new File("resources/"+soundtrackLocation);
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