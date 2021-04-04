package polis.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MusicPlayer {

    private static final String soundtrackLocation = "resources/polis/music/soundtracks/";
    private final ArrayList<Media> tracks;

    private MediaPlayer mediaPlayer;
    private boolean muted;

    public MusicPlayer() {

        ArrayList<String> filenames = new ArrayList<>();
        Collections.addAll(filenames,
                "BitThink_HeatleyBros.wav",
                "BlossomTown_HeatleyBros.wav",
                "Chillout_HeatleyBros.wav",
                "DayDream_HeatleyBros.wav",
                "UpliftingCity_HeatleyBros.wav"
        );
        tracks = new ArrayList<>();
        for (String s : filenames) {
            tracks.add(new Media(new File(soundtrackLocation+s).toURI().toString()));
        }

        muted = false;

        Media s = selectRandomMusic();
        changeMusic(s);
    }

    public void changeMusic(Media musicFile){
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setMute(muted);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::newMusic);
    }

    public void newMusic(){
        Media s = selectRandomMusic();
        changeMusic(s);
    }

    public void switchMute(){
        muted = !muted;
        mediaPlayer.setMute(muted);
    }

    public boolean getMuted(){
        return muted;
    }

    private Media selectRandomMusic(){
        Collections.shuffle(tracks);
        return tracks.get(0);
    }

}