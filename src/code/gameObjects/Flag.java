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

        BufferedImage[] tempSprites = new BufferedImage[MAX_FRAME];
        try {

            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_0.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_1.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/flag/flag_2.png"));
            System.out.println("Holi");

        } catch (Exception e) {

            System.out.println("adiosi");
            e.printStackTrace();
        }

    }

    public Flag(Vector2 position) {
        super(position);
        getSprites();
    }

}
