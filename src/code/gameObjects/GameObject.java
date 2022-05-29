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

    protected Vector2 position;
    private final BufferedImage[] sprites;
    private static int frame = 0;
    public static final int MAX_FRAME = 3;
    private final GameObjectSprite objectType;
    protected int sep;

    public GameObject(Vector2 position, GameObjectSprite objectType) {
        this.objectType = objectType;
        this.position = position;
        this.sprites = objectType.getSprites();
        sep = 1;

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

    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }

    /**
     * @return the objectType
     */
    public GameObjectSprite getObjectType() {
        return objectType;
    }

    /**
     * @return the position
     */
    public Vector2 getPosition() {
        return position;
    }

}
