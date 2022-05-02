package code.gameObjects;

import static code.gameObjects.GameObject.MAX_FRAME;
import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Flag extends GameObject {

    @Override
    public void getSprites() {

        sprites = new BufferedImage[MAX_FRAME];
        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_0.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_1.png"));
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Flag(Vector2 position) {
        super(position);
        getSprites();
    }

}
