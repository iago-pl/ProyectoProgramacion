package code.gameObjects;

import static code.gameObjects.GameObject.MAX_FRAME;
import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Key extends Entity {

    @Override
    public void getSprites() {

        BufferedImage[] tempSprites = new BufferedImage[MAX_FRAME];
        try {

            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/key/key_0.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/key/key_1.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/key/key_2.png"));
            System.out.println("Holi");

        } catch (Exception e) {

            System.out.println("adiosi");
            e.printStackTrace();
        }

    }

    public Key(Vector2 position) {
        super(position);
        getSprites();
    }

}
