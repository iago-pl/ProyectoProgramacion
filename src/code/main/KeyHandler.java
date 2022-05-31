package code.main;

import code.transform.Vector2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private static int frame = 0;

    private static final int updateRate = 3;

    @Override
    public void keyPressed(KeyEvent e) {

        if (ReferenceController.player == null || ReferenceController.mapController.isLoading() || ReferenceController.mapController.isEnded()) {
            return;
        }

        if (frame >= updateRate) {
            frame = 0;
        }

        if (frame % updateRate == 0 || frame == 0) {

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_R:
                    ReferenceController.mapController.loadSnapshot();
                    break;

                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (ReferenceController.player.move(new Vector2(0, -1))) {
                        ReferenceController.infoController.increaseSteps();
                        ReferenceController.infoController.updateInfo();
                    }
                    ReferenceController.mapController.takeSnapshot();
                    break;

                case KeyEvent.VK_S:

                case KeyEvent.VK_DOWN:
                    if (ReferenceController.player.move(new Vector2(0, 1))) {
                        ReferenceController.infoController.increaseSteps();
                        ReferenceController.infoController.updateInfo();
                    }
                    ReferenceController.mapController.takeSnapshot();
                    break;

                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (ReferenceController.player.move(new Vector2(-1, 0))) {
                        ReferenceController.infoController.increaseSteps();
                        ReferenceController.infoController.updateInfo();
                    }
                    ReferenceController.mapController.takeSnapshot();
                    break;

                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (ReferenceController.player.move(new Vector2(1, 0))) {
                        ReferenceController.infoController.increaseSteps();
                        ReferenceController.infoController.updateInfo();
                    }
                    ReferenceController.mapController.takeSnapshot();
                    break;

            }
        }
        frame++;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        frame = 0;
    }

}
