package polis.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Verantwoordelijk voor alles gerelateerd aan muziek afspelen.
 * **/

public class MusicPlayer {

    private final ArrayList<Media> tracks;

    private MediaPlayer mediaPlayer;
    private boolean muted;

    public MusicPlayer() {
        tracks = new ArrayList<>();
        File dir = new File("resources/polis/music/soundtracks/");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                tracks.add(new Media(new File(f.getPath()).toURI().toString()));
            }
            muted = false;
            changeMusic(selectRandomMusic());
        }
    }

    private void changeMusic(Media musicFile) {
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setMute(muted);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::newMusic);
    }

    private void newMusic() {
        changeMusic(selectRandomMusic());
    }

    public void switchMute() {
        if (tracks != null) {
            muted = !muted;
            mediaPlayer.setMute(muted);
        }
    }

    private Media selectRandomMusic() {
        Collections.shuffle(tracks);
        return tracks.get(0);
    }

}