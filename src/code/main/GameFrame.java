package code.main;

import code.transform.Vector2;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author a21iagopl
 */
public class GameFrame extends JPanel implements Runnable {

    public static final int BASE_TILE_SIZE = 24;
    public static final int SCALE = 4;

    public static final int TILE_SIZE = (BASE_TILE_SIZE * SCALE);
    public static final Vector2 TILE_SCREEN_SIZE = new Vector2(10, 7);

    public static final Vector2 SCREEN_SIZE = new Vector2(TILE_SCREEN_SIZE.x * TILE_SIZE, TILE_SCREEN_SIZE.y * TILE_SIZE);

    Thread gameThread;

    public GameFrame() {
        setPreferredSize(new Dimension(SCREEN_SIZE.x, SCREEN_SIZE.y));
        setBackground(Color.red);
        setDoubleBuffered(true);

    }

    @Override
    public void run() {
        while (gameThread != null) {            
            
        }
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update(){
        
    }
    
    public void paintComponent(){
        
    }

}
