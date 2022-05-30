package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author a21iagopl
 */
public class PlayerEntity extends Entity {

    public PlayerEntity(Vector2 position) {
        super(position, GameObjectSprite.PLAYER);
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
                    return true;
                } else {
                    return false;
                }
            case FLAG:
                changePosition(newPosition);
                playMoveSound();
                ReferenceController.audioController.play(2);
                ReferenceController.mapController.loadMap();
                return true;
            case MONSTER:
                ReferenceController.audioController.play(6);
                ReferenceController.audioController.play(7);
                ReferenceController.mapController.reloadMap();
                return false;
            default:
                return false;
        }
    }

    @Override
    protected void playMoveSound() {
        ReferenceController.audioController.play(0);
    }

}
