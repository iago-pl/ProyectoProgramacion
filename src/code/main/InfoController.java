package code.temp;

import code.gameObjects.GameObject;
import code.gameObjects.GameObjectType;
import code.transform.Vector2;

/**
 *
 * @author a21iagopl
 */
public class InfoController {

    GameObject[] infoBar = new GameObject[GameFrame.TILE_SCREEN_SIZE.x];

    int level;
    int steps;

    private final int yPos = 0;

    public InfoController() {
        loadInfo();
    }

    private void loadInfo() {

        infoBar[0] = new GameObject(new Vector2(0, yPos), GameObjectType.LETTL, 0);
        infoBar[1] = new GameObject(new Vector2(1, yPos), GameObjectType.LETTV, 0);
        infoBar[2] = new GameObject(new Vector2(2, yPos), GameObjectType.LETTL, 0);
        infoBar[3] = new GameObject(new Vector2(3, yPos), GameObjectType.LETTSEP, 0);
        updateInfo();
    }

    public void updateInfo() {

        if (level > 99) {
            level = 99;
        } else if (level < 0) {
            level = 0;
        }

        if (level < 10) {
            infoBar[4] = new GameObject(new Vector2(4, yPos), getNumObject(level), 0);
        } else {
            infoBar[4] = new GameObject(new Vector2(4, yPos), getNumObject(level / 10), 0);
            infoBar[5] = new GameObject(new Vector2(5, yPos), getNumObject(level - ((level / 10) * 10)), 0);
        }

        if (steps > 200) {
            steps = 200;

        } else if (steps < 0) {
            steps = 0;
        }

        if (steps < 10) {
            infoBar[infoBar.length - 1] = new GameObject(new Vector2(infoBar.length - 1, yPos), getNumObject(steps), 0);
        } else if (steps < 100) {
            infoBar[infoBar.length - 2] = new GameObject(new Vector2(infoBar.length - 2, yPos), getNumObject(steps / 10), 0);
            infoBar[infoBar.length - 1] = new GameObject(new Vector2(infoBar.length - 1, yPos), getNumObject(steps - ((steps / 10) * 10)), 0);
        } else {
            infoBar[infoBar.length - 3] = new GameObject(new Vector2(infoBar.length - 3, yPos), getNumObject(steps / 100), 0);
            infoBar[infoBar.length - 2] = new GameObject(new Vector2(infoBar.length - 2, yPos), getNumObject((steps / 10) - ((steps / 100) * 10)), 0);

            infoBar[infoBar.length - 1] = new GameObject(new Vector2(infoBar.length - 1, yPos), getNumObject(steps - ((steps / 10) * 10)), 0);
        }

    }

    public void increaseSteps() {
        steps++;

    }

    private GameObjectType getNumObject(int num) {
        switch (num) {
            case 0:
                return GameObjectType.NUM0;
            case 1:
                return GameObjectType.NUM1;
            case 2:
                return GameObjectType.NUM2;
            case 3:
                return GameObjectType.NUM3;
            case 4:
                return GameObjectType.NUM4;
            case 5:
                return GameObjectType.NUM5;
            case 6:
                return GameObjectType.NUM6;
            case 7:
                return GameObjectType.NUM7;
            case 8:
                return GameObjectType.NUM8;
            case 9:
                return GameObjectType.NUM9;
            default:
                return null;
        }
    }

}
