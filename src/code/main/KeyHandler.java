package code.main;

import code.gameObjects.Entity;
import code.transform.Vector2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author a21iagopl
 */
public class KeyHandler implements KeyListener {

    MapController mp;
    public static Entity player;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private boolean canPress;

    public KeyHandler(MapController mp) {
        this.mp = mp;
        canPress = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (mp.isLoading() || !canPress) {
            return;
        }

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                //mp.changeMap();
                canPress = false;
                break;
            case KeyEvent.VK_W:
                player.move(new Vector2(0, -1));
                canPress = false;
                break;
            case KeyEvent.VK_S:
                player.move(new Vector2(0, 1));
                canPress = false;
                break;
            case KeyEvent.VK_A:
                player.move(new Vector2(-1, 0));
                canPress = false;
                break;
            case KeyEvent.VK_D:
                player.move(new Vector2(1, 0));
                canPress = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                canPress = true;
            default:
                break;
        }
    }

}
