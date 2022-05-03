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

    private boolean rPress, wPress, sPress, aPress, dPress;

    public KeyHandler() {
        rPress = true;
        wPress = true;
        sPress = true;
        aPress = true;
        dPress = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_R && rPress) {
            System.out.println("xd");
            rPress = false;
        } else if (key == KeyEvent.VK_W && wPress) {

            wPress = false;

        } else if (key == KeyEvent.VK_S && sPress) {

            sPress = false;

        } else if (key == KeyEvent.VK_A && aPress) {

            aPress = false;

        } else if (key == KeyEvent.VK_D && dPress) {

            dPress = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                rPress = true;
                break;
            case KeyEvent.VK_W:
                wPress = true;
                break;
            case KeyEvent.VK_S:
                sPress = true;
                break;
            case KeyEvent.VK_A:
                aPress = true;
                break;
            case KeyEvent.VK_D:
                dPress = true;
                break;
            default:
                break;
        }
    }

}
