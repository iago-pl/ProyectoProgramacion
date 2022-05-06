package code.main;

import code.transform.Vector2;
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

    private boolean canPressR, canPressW, canPressS, canPressA, canPressD;

    public KeyHandler() {
        canPressR = true;
        canPressW = true;
        canPressS = true;
        canPressA = true;
        canPressD = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (ReferenceController.mapController.isLoading() || !canPressR || !canPressW || !canPressS || !canPressA || !canPressD) {
            return;
        }

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                //mp.changeMap();
                canPressR = false;
                break;
            case KeyEvent.VK_W:
                ReferenceController.player.move(new Vector2(0, -1));
                ReferenceController.infoController.increaseSteps();
                ReferenceController.infoController.updateInfo();
                canPressW = false;
                break;
            case KeyEvent.VK_S:
                ReferenceController.player.move(new Vector2(0, 1));
                ReferenceController.infoController.increaseSteps();
                ReferenceController.infoController.updateInfo();
                canPressS = false;
                break;
            case KeyEvent.VK_A:
                ReferenceController.player.move(new Vector2(-1, 0));
                ReferenceController.infoController.increaseSteps();
                ReferenceController.infoController.updateInfo();
                canPressA = false;
                break;
            case KeyEvent.VK_D:
                ReferenceController.player.move(new Vector2(1, 0));
                ReferenceController.infoController.increaseSteps();
                ReferenceController.infoController.updateInfo();
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
