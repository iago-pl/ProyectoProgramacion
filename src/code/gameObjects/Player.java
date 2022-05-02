package code.gameObjects;

import code.main.GameFrame;
import code.transform.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
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

        sprites = new BufferedImage[MAX_FRAME];
        
        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_0.png"));
            sprites[1] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_1.png"));
            sprites[2] = ImageIO.read(getClass().getResourceAsStream("/resources/img/player/player_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprites[frame], position.x * GameFrame.TILE_SIZE, position.y * GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, null);
    }

}
