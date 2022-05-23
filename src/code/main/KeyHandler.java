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

    private int frame = 0;

    @Override
    public void keyPressed(KeyEvent e) {

        if (ReferenceController.player == null) {
            return;
        }

        if (frame % 3 == 0 || frame == 0) {

            System.out.println(frame);

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
