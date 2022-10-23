package uet.oop.bomberman.sound;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    static final String eatItemSound = "res/sounds/eatItem.wav";
    static final String bombExplodedSound = "res/sounds/bombExploded2.wav";
    static final String buttonClickSound = "res/sounds/buttonClick.mp3";
    static final String enemyDieSound = "res/sounds/enemyDie.mp3";
    static final String killAllEnemiesSound = "res/sounds/killAllEnemies.wav";
    static final String setBombSound = "res/sounds/setBomb.wav";
    static final String charDieSound = "res/sounds/charDie.mp3";
    static final String loseLevelSound = "res/sounds/loseLevel.wav";
    static final String stageStartSound = "res/sounds/stageStart.mp3";
    public static AudioClip eatItemAudio = new AudioClip((new File(eatItemSound)).toURI().toString());
    public static AudioClip bombExplodedAudio = new AudioClip((new File(bombExplodedSound)).toURI().toString());
    public static AudioClip buttonClickAudio = new AudioClip((new File(buttonClickSound)).toURI().toString());
    public static AudioClip enemyDieAudio = new AudioClip((new File(enemyDieSound)).toURI().toString());
    public static AudioClip killAllEnemiesAudio = new AudioClip((new File(killAllEnemiesSound)).toURI().toString());
    public static AudioClip setBombAudio = new AudioClip((new File(setBombSound)).toURI().toString());
    public static AudioClip charDieAudio = new AudioClip((new File(charDieSound)).toURI().toString());
    public static AudioClip loseLevelAudio = new AudioClip((new File(loseLevelSound)).toURI().toString());
    public static AudioClip stageStartAudio = new AudioClip((new File(stageStartSound)).toURI().toString());
    static final String MainBGM = "res/sounds/MainBGM.mp3";
    static Media sound = new Media((new File(MainBGM)).toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(sound);

    public Sound() {
    }

    public static void playBGM() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
