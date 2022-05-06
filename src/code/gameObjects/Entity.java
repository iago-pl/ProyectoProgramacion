package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;
import java.util.ArrayList;

/**
 *
 * @author a21rebecanf
 */
public class Entity extends GameObject {

    public ArrayList<Vector2> lastPositionList;

    public Entity(Vector2 position, GameObjectType objectType, int sep) {
        super(position, objectType, sep);
    }

    public boolean move(Vector2 pos) {

        Vector2 newPosition = new Vector2(pos.x + position.x, pos.y + position.y);

        if (newPosition.x < 0 || newPosition.x >= ReferenceController.mapController.gameObjects.length || newPosition.y < 0 || newPosition.y >= ReferenceController.mapController.gameObjects[0].length) {
            return false;
        }

        if (ReferenceController.mapController.gameObjects[newPosition.x][newPosition.y] == null) {
            changePosition(newPosition);
            return true;
        } else {

            return checkCollision(newPosition, pos);
        }
    }

    protected boolean checkCollision(Vector2 newPosition, Vector2 pos) {
        GameObject temp = ReferenceController.mapController.gameObjects[newPosition.x][newPosition.y];
        switch (temp.objectType) {
            default:
                return false;
        }
    }

    protected void changePosition(Vector2 newPosition) {
        Vector2 lastPosition = new Vector2(position.x, position.y);
        position = newPosition;
        ReferenceController.mapController.gameObjects[position.x][position.y] = this;
        ReferenceController.mapController.gameObjects[lastPosition.x][lastPosition.y] = null;
    }

}
