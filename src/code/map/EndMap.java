package code.map;

import code.gameObjects.GameObject;
import code.gameObjects.GameObjectSprite;
import code.gameObjects.UI;
import code.main.InfoController;
import code.main.ReferenceController;
import code.transform.Vector2;

/**
 *
 * @author a21iagopl
 */
public class EndMap extends Map {

    public EndMap() {
        super(null, null);

        background = generateLayer();
    }

    private MapLayer generateLayer() {
        MapLayer out = new MapLayer();

        int level = ReferenceController.infoController.getLevel();

        if (level < 10) {
            out.level[4][2] = new GameObject(new Vector2(4, 2), InfoController.getNumObject(level));
        } else if (level < 100) {
            out.level[3][2] = new GameObject(new Vector2(3, 2), InfoController.getNumObject(level / 10));
            out.level[4][2] = new GameObject(new Vector2(4, 2), InfoController.getNumObject(level - ((level / 10) * 10)));
        }
        
        out.level[6][2] = new GameObject(new Vector2(6, 2), GameObjectSprite.TILEL);
        out.level[7][2] = new GameObject(new Vector2(7, 2), GameObjectSprite.TILEE);
        out.level[8][2] = new GameObject(new Vector2(8, 2), GameObjectSprite.TILEV);
        out.level[9][2] = new GameObject(new Vector2(9, 2), GameObjectSprite.TILEE);
        out.level[10][2] = new GameObject(new Vector2(10, 2), GameObjectSprite.TILEL);

        if (ReferenceController.infoController.getLevel() > 1) {
            out.level[11][2] = new GameObject(new Vector2(11, 2), GameObjectSprite.TILES);
        }

        out.level[3][4] = new GameObject(new Vector2(3, 4), GameObjectSprite.TILEC);
        out.level[4][4] = new GameObject(new Vector2(4, 4), GameObjectSprite.TILEO);
        out.level[5][4] = new GameObject(new Vector2(5, 4), GameObjectSprite.TILEM);
        out.level[6][4] = new GameObject(new Vector2(6, 4), GameObjectSprite.TILEP);
        out.level[7][4] = new GameObject(new Vector2(7, 4), GameObjectSprite.TILEL);
        out.level[8][4] = new GameObject(new Vector2(8, 4), GameObjectSprite.TILEE);
        out.level[9][4] = new GameObject(new Vector2(9, 4), GameObjectSprite.TILET);
        out.level[10][4] = new GameObject(new Vector2(10, 4), GameObjectSprite.TILEE);
        out.level[11][4] = new GameObject(new Vector2(11, 4), GameObjectSprite.TILED);

        int steps = ReferenceController.infoController.getSteps() + 1;

        if (steps < 10) {
            out.level[5][6] = new GameObject(new Vector2(5, 6), InfoController.getNumObject(steps));
        } else if (steps < 100) {
            out.level[4][6] = new GameObject(new Vector2(4, 6), InfoController.getNumObject(steps / 10));
            out.level[5][6] = new GameObject(new Vector2(5, 6), InfoController.getNumObject(steps - ((steps / 10) * 10)));
        } else {
            out.level[3][6] = new GameObject(new Vector2(3, 6), InfoController.getNumObject(steps / 100));
            out.level[4][6] = new GameObject(new Vector2(4, 6), InfoController.getNumObject((steps / 10) - ((steps / 100) * 10)));
            out.level[5][6] = new GameObject(new Vector2(5, 6), InfoController.getNumObject(steps - ((steps / 10) * 10)));
        }

        out.level[7][6] = new GameObject(new Vector2(7, 6), GameObjectSprite.TILES);
        out.level[8][6] = new GameObject(new Vector2(8, 6), GameObjectSprite.TILET);
        out.level[9][6] = new GameObject(new Vector2(9, 6), GameObjectSprite.TILEE);
        out.level[10][6] = new GameObject(new Vector2(10, 6), GameObjectSprite.TILEP);

        if (ReferenceController.infoController.getSteps() > 1) {
            out.level[11][6] = new GameObject(new Vector2(11, 6), GameObjectSprite.TILES);
        }

        return out;
    }

}
