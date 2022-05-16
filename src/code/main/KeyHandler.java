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
        
        if (ReferenceController.player == null) {
            return;
        }

        if (ReferenceController.mapController.isLoading() || !canPressR || !canPressW || !canPressS || !canPressA || !canPressD) {
            return;
        }

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_R:
                ReferenceController.mapController.loadSnapshot();
                canPressR = false;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (ReferenceController.player.move(new Vector2(0, -1))) {
                    ReferenceController.infoController.increaseSteps();
                    ReferenceController.infoController.updateInfo();
                }
                ReferenceController.mapController.takeSnapshot();

                canPressW = false;
                break;
            case KeyEvent.VK_S:

            case KeyEvent.VK_DOWN:
                if (ReferenceController.player.move(new Vector2(0, 1))) {
                    ReferenceController.infoController.increaseSteps();
                    ReferenceController.infoController.updateInfo();
                }
                ReferenceController.mapController.takeSnapshot();

                canPressS = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (ReferenceController.player.move(new Vector2(-1, 0))) {
                    ReferenceController.infoController.increaseSteps();
                    ReferenceController.infoController.updateInfo();
                }

                ReferenceController.mapController.takeSnapshot();
                canPressA = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (ReferenceController.player.move(new Vector2(1, 0))) {
                    ReferenceController.infoController.increaseSteps();
                    ReferenceController.infoController.updateInfo();
                }

                ReferenceController.mapController.takeSnapshot();
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
            case KeyEvent.VK_UP:
                canPressW = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                canPressS = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                canPressA = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                canPressD = true;
                break;
            default:
                break;
        }
    }

}
