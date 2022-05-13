package code.gameObjects;

import code.main.GameFrame;
import code.transform.Vector2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author a21rebecanf
 */
public class GameObject {

    public Vector2 position;
    public BufferedImage[] sprites;
    public static int frame = 0;
    public static final int MAX_FRAME = 3;
    public GameObjectSprite objectType;
    public int sep;

    public GameObject(Vector2 position, GameObjectSprite objectType, int sep) {
        this.objectType = objectType;
        this.position = position;
        this.sprites = objectType.sprites;
        this.sep = sep;

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprites[frame], position.x * GameFrame.TILE_SIZE, position.y * GameFrame.TILE_SIZE + (GameFrame.TILE_SIZE * sep), GameFrame.TILE_SIZE, GameFrame.TILE_SIZE, null);
    }

    public static void changeFrame() {
        frame++;

        if (frame >= MAX_FRAME) {
            frame = 0;
        }

    }

}
