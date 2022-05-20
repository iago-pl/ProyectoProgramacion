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
    private final URL[] sounds = new URL[10];

    public AudioController() {
        loadAudio();
    }

    private void loadAudio() {

        sounds[0] = getClass().getResource("/resources/aud/move.wav");
        sounds[1] = getClass().getResource("/resources/aud/reverse.wav");
        sounds[2] = getClass().getResource("/resources/aud/flag.wav");
        sounds[3] = getClass().getResource("/resources/aud/lock.wav");
        sounds[4] = getClass().getResource("/resources/aud/move_box.wav");
        sounds[5] = getClass().getResource("/resources/aud/move_key.wav");
        sounds[6] = getClass().getResource("/resources/aud/box_break.wav");
        sounds[7] = getClass().getResource("/resources/aud/key_break.wav");
        sounds[8] = getClass().getResource("/resources/aud/die.wav");
        sounds[9] = getClass().getResource("/resources/aud/enemy.wav");

        for (URL sound : sounds) {
            AudioInputStream temp;
            try {
                temp = AudioSystem.getAudioInputStream(sound);
                clip = AudioSystem.getClip();
                clip.open(temp);
            }catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void play(int soundIndex) {

        if (soundIndex >= sounds.length) {
            return;
        }

        try {
            clip.start();
            AudioInputStream temp = AudioSystem.getAudioInputStream(sounds[soundIndex]);
            clip = AudioSystem.getClip();
            clip.open(temp);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
