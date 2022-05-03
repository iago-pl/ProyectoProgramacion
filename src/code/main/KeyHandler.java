package code.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author a21iagopl
 */
public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_R) {
            
        } else if (key == KeyEvent.VK_W) {

        } else if (key == KeyEvent.VK_S) {

        } else if (key == KeyEvent.VK_A) {

        } else if (key == KeyEvent.VK_D) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
