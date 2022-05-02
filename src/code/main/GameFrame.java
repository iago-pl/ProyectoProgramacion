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
 * @author a21iagopl
 */
public class GameFrame extends JPanel implements Runnable {

    public static final int BASE_TILE_SIZE = 24;
    public static final int SCALE = 2;

    public static final int TILE_SIZE = (BASE_TILE_SIZE * SCALE);
    public static final Vector2 TILE_SCREEN_SIZE = new Vector2(15, 10);

    public static final Vector2 SCREEN_SIZE = new Vector2(TILE_SCREEN_SIZE.x * TILE_SIZE, TILE_SCREEN_SIZE.y * TILE_SIZE);

    public static final int FPS = 60;

    Thread gameThread;

    KeyHandler keyHand = new KeyHandler();

    MapController mapController = new MapController();

    public GameFrame() {
        setPreferredSize(new Dimension(SCREEN_SIZE.x, SCREEN_SIZE.y));
        setBackground(Color.red);
        setDoubleBuffered(true);
        addKeyListener(keyHand);
        setFocusable(true);

    }

    @Override
    public void run() {

        double drawInteval = 1000000000 / FPS;
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

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
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

        for (int i = 0; i < TILE_SCREEN_SIZE.x; i++) {
            for (int j = 0; j < TILE_SCREEN_SIZE.y; j++) {

                if (MapController.background[i][j] != null) {
                    MapController.background[i][j].draw(g2);
                }
                if (MapController.gameObjets[i][j] != null) {
                    MapController.gameObjets[i][j].draw(g2);
                }
            }
        }

        if (true) {

        }

        g2.dispose();
    }

}
