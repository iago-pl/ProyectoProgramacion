package code.gameObjects;

import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class testLetter extends GameObject {

    public testLetter(Vector2 position) {
        super(position);
        getSprites();

    }

    @Override
    public void getSprites() {

        sprites = new BufferedImage[MAX_FRAME];

        try {

            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/characters/numbers/0/0_0.png"));
            sprites[1] = ImageIO.read(getClass().getResourceAsStream("/resources/img/characters/numbers/0/0_1.png"));
            sprites[2] = ImageIO.read(getClass().getResourceAsStream("/resources/img/characters/numbers/0/0_2.png"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
