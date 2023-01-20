package code.gameObjects;

import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class Entity extends GameObject {

    public Entity(Vector2 position, GameObjectSprite objectType) {
        super(position, objectType);
    }

    public boolean move(Vector2 pos) {

        Vector2 newPosition = Vector2.add(pos, position);

        if (newPosition.x < 0 || newPosition.x >= ReferenceController.gameFrame.TILE_SCREEN_SIZE.x || newPosition.y < 0 || newPosition.y >= ReferenceController.gameFrame.TILE_SCREEN_SIZE.y - 1) {
            return false;
        }

        if (ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[newPosition.x][newPosition.y] == null) {
            changePosition(newPosition);
            playMoveSound();
            return true;
        } else {

            return checkCollision(newPosition, pos);
        }
    }

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
            case MONSTER:
                ReferenceController.audioController.play(7);
                ReferenceController.audioController.play(9);
                clearBoth(newPosition);
                return true;
            default:
                return false;
        }
    }

    protected void playMoveSound() {
        ReferenceController.audioController.play(4);
    }

    protected void changePosition(Vector2 newPosition) {
        Vector2 lastPosition = new Vector2(position.x, position.y);
        setPosition(newPosition);
        ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[position.x][position.y] = this;
        ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[lastPosition.x][lastPosition.y] = null;
    }

    protected void clearBoth(Vector2 newPosition) {
        Vector2 lastPosition = new Vector2(position.x, position.y);
        ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[newPosition.x][newPosition.y] = null;
        ReferenceController.mapController.getCurrentMap().getPlayground().getLevel()[lastPosition.x][lastPosition.y] = null;
    }
}