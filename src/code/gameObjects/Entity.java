package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author a21rebecanf
 */
public class Entity extends GameObject {

    public Entity(Vector2 position, GameObjectType objectType) {
        super(position, objectType, 1);
    }

    public boolean move(Vector2 pos) {

        Vector2 newPosition = new Vector2(pos.x + position.x, pos.y + position.y);

        if (newPosition.x < 0 || newPosition.x >= ReferenceController.mapController.gameObjects.level.length || newPosition.y < 0 || newPosition.y >= ReferenceController.mapController.gameObjects.level[0].length) {
            return false;
        }

        if (ReferenceController.mapController.gameObjects.level[newPosition.x][newPosition.y] == null) {
            changePosition(newPosition);
            return true;
        } else {

            return checkCollision(newPosition, pos);
        }
    }

    protected boolean checkCollision(Vector2 newPosition, Vector2 pos) {
        GameObject temp = ReferenceController.mapController.gameObjects.level[newPosition.x][newPosition.y];
        switch (temp.objectType) {
            case BOX:
            case KEY:
                Entity tempEntity = (Entity) temp;
                if (tempEntity.move(pos)) {
                    changePosition(newPosition);
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    protected void changePosition(Vector2 newPosition) {
        Vector2 lastPosition = new Vector2(position.x, position.y);
        position = newPosition;
        ReferenceController.mapController.gameObjects.level[position.x][position.y] = this;
        ReferenceController.mapController.gameObjects.level[lastPosition.x][lastPosition.y] = null;
    }

}
