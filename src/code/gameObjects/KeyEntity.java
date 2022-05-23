package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author a21rebecanf
 */
public class KeyEntity extends Entity {

    public KeyEntity(Vector2 position) {
        super(position, GameObjectSprite.KEY);
        sep = 1;
    }

    @Override
    protected boolean checkCollision(Vector2 newPosition, Vector2 pos) {
        GameObject temp = ReferenceController.mapController.currentMap.playground.level[newPosition.x][newPosition.y];

        switch (temp.objectType) {
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

    @Override
    protected void changePosition(Vector2 newPosition) {
        Vector2 lastPosition = new Vector2(position.x, position.y);
        position = newPosition;
        ReferenceController.mapController.currentMap.playground.level[position.x][position.y] = this;
        ReferenceController.mapController.currentMap.playground.level[lastPosition.x][lastPosition.y] = null;
    }

}
