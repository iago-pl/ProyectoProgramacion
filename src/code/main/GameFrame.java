package code.main;

import code.gameObjects.GameObject;
import code.transform.Vector2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author a21iagopl
 */
public class GameFrame extends JPanel implements Runnable {

    static final int BASE_TILE_SIZE = 24;
    static final int SCALE = 2;

    public static final int TILE_SIZE = (BASE_TILE_SIZE * SCALE);
    public static final Vector2 TILE_SCREEN_SIZE = new Vector2(15, 10);

    public static final Vector2 SCREEN_SIZE = new Vector2(TILE_SCREEN_SIZE.x * TILE_SIZE, TILE_SCREEN_SIZE.y * TILE_SIZE);

    public static final int FPS = 60;
    private static final int NANO_TIME = 1000000000;

    Thread gameThread;

    public GameFrame() {
        ReferenceController.mapController = new MapController();
        ReferenceController.keyHandler = new KeyHandler();
        
        setPreferredSize(new Dimension(SCREEN_SIZE.x, SCREEN_SIZE.y));
        setBackground(Color.red);
        setDoubleBuffered(true);
        addKeyListener(ReferenceController.keyHandler);
        setFocusable(true);

    }

    public void sleepThread(int milis) {

        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < ReferenceController.mapController.gameObjects.length; i++) {
            for (int j = 0; j < ReferenceController.mapController.gameObjects[0].length; j++) {

                if (ReferenceController.mapController.background[i][j] != null) {
                    ReferenceController.mapController.background[i][j].draw(g2);
                }

                if (ReferenceController.mapController.gameObjects[i][j] != null) {
                    ReferenceController.mapController.gameObjects[i][j].draw(g2);
                }

            }
        }

        g2.dispose();
    }

}
