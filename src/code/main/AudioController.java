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

    private final AudioInputStream[] sounds = new AudioInputStream[10];
    Clip clip;

    public AudioController() {
        System.out.println("ajustar tamaño array sonidos");
        loadAudio();
    }

    private void loadAudio() {

        try {
            sounds[0] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/move/move.wav"));
            sounds[1] = AudioSystem.getAudioInputStream(getClass().getResource("/resources/aud/move/rev_move.wav"));
        } catch (UnsupportedAudioFileException | IOException ex) {

            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play(int soundIndex) {

        try {
            clip = AudioSystem.getClip();
            clip.open(sounds[soundIndex]);
            clip.start();
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
