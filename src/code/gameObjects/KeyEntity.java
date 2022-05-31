package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class KeyEntity extends Entity {

    public KeyEntity(Vector2 position) {
        super(position, GameObjectSprite.KEY);
    }

    @Override
    protected boolean checkCollision(Vector2 newPosition, Vector2 pos) {
        GameObject temp = ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[newPosition.x][newPosition.y];

        switch (temp.getObjectType()) {
            case BOX:
            case KEY:
                Entity tempEntity = (Entity) temp;
                if (tempEntity.move(pos)) {
                    changePosition(newPosition);
                    playMoveSound();
                    return true;
                } else {
                    return false;
                }
            case LOCK:
                clearBoth(newPosition);
                ReferenceController.audioController.play(3);
                return true;
            case MONSTER:
                ReferenceController.audioController.play(6);
                ReferenceController.audioController.play(9);
                clearBoth(newPosition);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void playMoveSound() {
        ReferenceController.audioController.play(5);
    }

}
