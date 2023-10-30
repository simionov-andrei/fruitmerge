package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static main.Game.State;

import java.io.File;

public class PlayMenuMusic{

    public static final String PlayMenuMusic = null;
    public static Clip menuMusic;

    public PlayMenuMusic() {
        
        // Load and play the background music
        playMenuMusic("res/menuMusic.wav");
    }

    public static void playMenuMusic(String musicFile) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());
            menuMusic = AudioSystem.getClip();
            menuMusic.open(audioInputStream);
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            menuMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMenuMusic() {
        
        if (State != Game.STATE.MENU) {
            menuMusic.stop();
        }
    }
}
