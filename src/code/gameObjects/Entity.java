package code.gameObjects;

import code.transform.Vector2;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author a21rebecanf
 */
public class Entity extends GameObject {

    public ArrayList<Vector2> lastPositionList;

    public boolean move(Vector2 pos) {

        return false;

    }

    public Entity(Vector2 position) {
        super(position);
    }

}
