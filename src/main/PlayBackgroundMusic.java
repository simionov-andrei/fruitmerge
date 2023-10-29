package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static main.Game.State;

import java.io.File;

public class PlayBackgroundMusic {
    
    public static final String PlayBackgroundMusic = null;
    public static Clip backgroundMusic;

    public PlayBackgroundMusic() {
        
        // Load and play the background music
        playBackgroundMusic("C:/University/Q1/Programming/fruitmerge/res/backgroundMusic.wav");
    }

    public static void playBackgroundMusic(String musicFile) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            backgroundMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        
        if (State != Game.STATE.GAME) {
            backgroundMusic.stop();
        }
    }
}