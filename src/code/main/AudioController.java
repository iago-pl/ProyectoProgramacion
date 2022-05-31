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
 * @author Rebeca Noya y Iago Pena
 */
public class AudioController {

    private static Clip clip;
    private static final URL[] soundsUrl = new URL[10];
    private static final AudioInputStream[] sounds = new AudioInputStream[soundsUrl.length];

    public AudioController() {
        loadAudio();
    }

    private void loadAudio() {

        soundsUrl[0] = getClass().getResource("/resources/aud/move.wav");
        soundsUrl[1] = getClass().getResource("/resources/aud/reverse.wav");
        soundsUrl[2] = getClass().getResource("/resources/aud/flag.wav");
        soundsUrl[3] = getClass().getResource("/resources/aud/lock.wav");
        soundsUrl[4] = getClass().getResource("/resources/aud/move_box.wav");
        soundsUrl[5] = getClass().getResource("/resources/aud/move_key.wav");
        soundsUrl[6] = getClass().getResource("/resources/aud/key_break.wav");
        soundsUrl[7] = getClass().getResource("/resources/aud/box_break.wav");
        soundsUrl[8] = getClass().getResource("/resources/aud/die.wav");
        soundsUrl[9] = getClass().getResource("/resources/aud/enemy.wav");

        for (int i = 0; i < soundsUrl.length; i++) {
            try {
                sounds[i] = AudioSystem.getAudioInputStream(soundsUrl[i]);
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("VERIFICAR QUE ESTO FUNCIONA");

    }

    public void play(int soundIndex) {

        if (soundIndex >= soundsUrl.length) {
            return;
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(sounds[soundIndex]);
            clip.start();
            sounds[soundIndex] = AudioSystem.getAudioInputStream(soundsUrl[soundIndex]);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
