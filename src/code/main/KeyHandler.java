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

    private boolean canPressR, canPressW, canPressS, canPressA, canPressD;

    public KeyHandler(MapController mp) {
        this.mp = mp;
        canPressR = true;
        canPressW = true;
        canPressS = true;
        canPressA = true;
        canPressD = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (mp.isLoading() || !canPressR || !canPressW || !canPressS || !canPressA || !canPressD) {
            return;
        }

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                //mp.changeMap();
                canPressR = false;
                break;
            case KeyEvent.VK_W:
                player.move(new Vector2(0, -1));
                canPressW = false;
                break;
            case KeyEvent.VK_S:
                player.move(new Vector2(0, 1));
                canPressS = false;
                break;
            case KeyEvent.VK_A:
                player.move(new Vector2(-1, 0));
                canPressA = false;
                break;
            case KeyEvent.VK_D:
                player.move(new Vector2(1, 0));
                canPressD = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                canPressR = true;
                break;
            case KeyEvent.VK_W:
                canPressW = true;
                break;
            case KeyEvent.VK_S:
                canPressS = true;
                break;
            case KeyEvent.VK_A:
                canPressA = true;
                break;
            case KeyEvent.VK_D:
                canPressD = true;
                break;
            default:
                break;
        }
    }

}
