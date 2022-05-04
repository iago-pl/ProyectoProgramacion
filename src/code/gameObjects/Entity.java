package code.gameObjects;

import code.main.GameFrame;
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

        if (newPosition.x < 0 || newPosition.x >= MapController.gameObjects.length || newPosition.y < 0 || newPosition.y >= MapController.gameObjects[0].length) {
            return false;
        }

        if (MapController.gameObjects[newPosition.x][newPosition.y] == null) {
            Vector2 lastPosition = new Vector2(position.x, position.y);
            position = newPosition;
            MapController.gameObjects[position.x][position.y] = this;
            MapController.gameObjects[lastPosition.x][lastPosition.y] = null;
            return true;
        } else {

            GameObject temp = MapController.gameObjects[newPosition.x][newPosition.y];

            if (temp.objectType == GameObjectType.LOCK) {
                return false;

            } else if (temp.objectType == GameObjectType.BOX || temp.objectType == GameObjectType.KEY) {
                Entity tempEntity = (Entity) temp;
                if (tempEntity.move(pos)) {
                    Vector2 lastPosition = new Vector2(position.x, position.y);
                    position = newPosition;
                    MapController.gameObjects[position.x][position.y] = this;
                    MapController.gameObjects[lastPosition.x][lastPosition.y] = null;
                    return true;

                } else {
                    return false;

                }
            }

        }

        return false;
    }

    public Entity(Vector2 position, GameObjectType objectType, int sep) {
        super(position, objectType, sep);

    }

}
