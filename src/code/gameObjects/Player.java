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
        getSprites();
        
    }
    
    @Override
    public void getSprites() {
        
        BufferedImage[] tempSprites = new BufferedImage[MAX_FRAME];
        try {
            
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_0.png"));
            tempSprites[1] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_1.png"));
            tempSprites[2] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_2.png"));
            System.out.println("Holi");
            
        } catch (Exception e) {
            
            System.out.println("adiosi");
            e.printStackTrace();
        }
        
    }
    
    @Override
    public boolean move(Vector2 pos) {

        return false;

    }
    
}
