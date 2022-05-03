package code.gameObjects;

import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Lock extends GameObject {

    @Override
    public void getSprites() {

        sprites = new BufferedImage[MAX_FRAME];
        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/lock/lock_0.png"));
            sprites[1] = ImageIO.read(getClass().getResourceAsStream("/resources/img/lock/lock_1.png"));
            sprites[2] = ImageIO.read(getClass().getResourceAsStream("/resources/img/lock/lock_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Lock(Vector2 position) {
        super(position);
        getSprites();
    }

}
