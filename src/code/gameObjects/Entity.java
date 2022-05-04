package code.gameObjects;

import code.main.MapController;
import code.transform.Vector2;
import java.util.ArrayList;

/**
 *
 * @author a21rebecanf
 */
public class Entity extends GameObject {

    public ArrayList<Vector2> lastPositionList;

    public boolean move(Vector2 pos) {

        Vector2 newPosition = new Vector2(pos.x + position.x, pos.y + position.y);
        
        
        
        if (MapController.gameObjects[newPosition.x][newPosition.y] == null) {
            Vector2 lastPosition = new Vector2(position.x, position.y);
            position = newPosition;
            MapController.gameObjects[pos.x][pos.y] = this;
            MapController.gameObjects[lastPosition.x][lastPosition.y] = null;
            return true;
        }

        return false;

    }

    public Entity(Vector2 position, GameObjectType objectType, int sep) {
        super(position, objectType, sep);

    }

}
