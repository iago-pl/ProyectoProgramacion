package code.gameObjects;

import static code.gameObjects.GameObject.MAX_FRAME;
import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Door extends GameObject {

    @Override
    public void getSprites() {

        sprites = new BufferedImage[MAX_FRAME];
        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/door/door_0.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/door/door_1.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/door/door_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Door(Vector2 position) {
        super(position);
        getSprites();
    }

}
