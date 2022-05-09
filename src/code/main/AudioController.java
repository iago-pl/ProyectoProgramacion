package code.main;

import java.io.IOException;
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
    private final AudioInputStream[] sounds = new AudioInputStream[4];

    public AudioController() {
        loadAudio();
    }

    private void loadAudio() {

        try {
            sounds[0] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/move.wav"));
            sounds[1] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/reverse.wav"));
            sounds[2] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/flag.wav"));
            sounds[3] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/lock.wav"));

        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play(int soundIndex) {

        if (soundIndex >= sounds.length) {
            return;
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(sounds[soundIndex]);
            clip.start();
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
