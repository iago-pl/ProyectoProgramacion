package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author a21iagopl
 */
public class PlayerEntity extends Entity {

    public PlayerEntity(Vector2 position) {
        super(position, GameObjectType.PLAYER, 1);
    }
    
    @Override
    protected boolean checkCollision(Vector2 newPosition, Vector2 pos) {
        System.out.println("player");
        GameObject temp = ReferenceController.mapController.gameObjects[newPosition.x][newPosition.y];

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

}
