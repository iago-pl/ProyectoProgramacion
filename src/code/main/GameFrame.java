package code.main;

import code.gameObjects.GameObject;
import code.transform.Vector2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class GameFrame extends JPanel implements Runnable {

    private static final int BASE_TILE_SIZE = 24;
    private static final int SCALE = 2;

    public static final int TILE_SIZE = (BASE_TILE_SIZE * SCALE);
    public static final Vector2 TILE_SCREEN_SIZE = new Vector2(15, 10);

    private static final Vector2 SCREEN_SIZE = new Vector2(TILE_SCREEN_SIZE.x * TILE_SIZE, TILE_SCREEN_SIZE.y * TILE_SIZE);

    private static final int FPS = 60;
    private static final int NANO_TIME = 1000000000;

    private Thread gameThread;

    public GameFrame() {
        setPreferredSize(new Dimension(SCREEN_SIZE.x, SCREEN_SIZE.y));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(ReferenceController.keyHandler);
        setFocusable(true);

    }

    @Override
    public void run() {

        double drawInteval = NANO_TIME / FPS;
        double deltaTime = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInteval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (deltaTime >= 1) {
                repaint();

                if (drawCount % (FPS / GameObject.MAX_FRAME) == 0) {

                    GameObject.changeFrame();

                }

                deltaTime--;
                drawCount++;
            }

            if (timer >= NANO_TIME) {
                //System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, TILE_SIZE - 2, SCREEN_SIZE.x, 2);

        for (int i = 0; i < ReferenceController.mapController.getCurrentMap().getPlayground().getLevel().length; i++) {

            if (ReferenceController.infoController.getInfoBar()[i] != null) {
                ReferenceController.infoController.getInfoBar()[i].draw(g2);
            }
            for (int j = 0; j < ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[0].length; j++) {

                if (ReferenceController.mapController.getCurrentMap().getBackground().getLevel()[i][j] != null) {
                    ReferenceController.mapController.getCurrentMap().getBackground().getLevel()[i][j].draw(g2);
                }

                if (ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[i][j] != null) {
                    ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[i][j].draw(g2);
                }

            }
        }

        g2.dispose();
    }

}
