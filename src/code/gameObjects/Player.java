package code.gameObjects;

import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Player extends Entity {
    
    public Player(Vector2 position) {
        super(position);
        
    }
    
    @Override
    public void getSprites() {
        
        BufferedImage[] tempSprites = new BufferedImage[MAX_FRAME];
        try {
            
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/player/Player_0.png"));
            tempSprites[1] = ImageIO.read(getClass().getResourceAsStream("/player/Player_1.png"));
            tempSprites[2] = ImageIO.read(getClass().getResourceAsStream("/player/Player_2.png"));
            
        } catch (Exception e) {
        }
        
    }
    
}
