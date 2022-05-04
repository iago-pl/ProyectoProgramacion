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

        System.out.println(newPosition.x + " " + newPosition.y);

        if (newPosition.x < 0 || newPosition.x > GameFrame.TILE_SCREEN_SIZE.x || newPosition.y < 0 || newPosition.y > GameFrame.TILE_SCREEN_SIZE.y) {
            System.out.println("fuera limites");
            return false;
        }

        if (MapController.gameObjects[newPosition.x][newPosition.y] == null) {
            System.out.println("mover");
            Vector2 lastPosition = new Vector2(position.x, position.y);
            position = newPosition;
            MapController.gameObjects[pos.x][pos.y] = this;
            MapController.gameObjects[lastPosition.x][lastPosition.y] = null;
            return true;
        } else {

            GameObject temp = MapController.gameObjects[newPosition.x][newPosition.y];

            if (temp.objectType == GameObjectType.LOCK) {
                return false;

            } else if (temp.objectType == GameObjectType.BOX || temp.objectType == GameObjectType.KEY) {
                System.out.println("entidad");
                Entity tempEntity = (Entity) temp;
                if (tempEntity.move(pos)) {
                    System.out.println("puede moverse");
                    Vector2 lastPosition = new Vector2(position.x, position.y);
                    position = newPosition;
                    MapController.gameObjects[pos.x][pos.y] = this;
                    MapController.gameObjects[lastPosition.x][lastPosition.y] = null;
                    return true;

                } else {
                     System.out.println("no puede moverse");
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
