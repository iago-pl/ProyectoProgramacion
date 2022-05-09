package code.main;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author a21iagopl
 */
public class AudioController {

    Clip clip;
    //private final AudioInputStream[] sounds = new AudioInputStream[4];
    private final URL[] sounds = new URL[4];

    public AudioController() {
        loadAudio();
    }

    private void loadAudio() {

        sounds[0] = getClass().getResource("/resources/aud/move.wav");
        sounds[1] = getClass().getResource("/resources/aud/reverse.wav");
        sounds[2] = getClass().getResource("/resources/aud/flag.wav");
        sounds[3] = getClass().getResource("/resources/aud/lock.wav");
    }

    public void play(int soundIndex) {

        if (soundIndex >= sounds.length) {
            return;
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sounds[soundIndex]));
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
