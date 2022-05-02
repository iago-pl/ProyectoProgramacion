package code.gameObjects;

import static code.gameObjects.GameObject.MAX_FRAME;
import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Box extends Entity {

    @Override
    public void getSprites() {

        sprites = new BufferedImage[MAX_FRAME];
        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_0.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_1.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Box(Vector2 position) {
        super(position);
        getSprites();
    }


}
