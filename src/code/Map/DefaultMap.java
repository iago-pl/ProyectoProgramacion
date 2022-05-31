package code.map;

import code.gameObjects.GameObject;
import code.gameObjects.GameObjectSprite;
import code.transform.Vector2;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class DefaultMap extends Map {

    public DefaultMap() {
        super(new MapLayer(), null);
        background = generateLayer();
    }

    private MapLayer generateLayer() {
        MapLayer out = new MapLayer();

        out.level[4][3] = new GameObject(new Vector2(4, 3), GameObjectSprite.TILEN);
        out.level[5][3] = new GameObject(new Vector2(5, 3), GameObjectSprite.TILEO);

        out.level[7][3] = new GameObject(new Vector2(7, 3), GameObjectSprite.TILEM);
        out.level[8][3] = new GameObject(new Vector2(8, 3), GameObjectSprite.TILEA);
        out.level[9][3] = new GameObject(new Vector2(9, 3), GameObjectSprite.TILEP);
        out.level[10][3] = new GameObject(new Vector2(10, 3), GameObjectSprite.TILES);

        out.level[5][5] = new GameObject(new Vector2(5, 5), GameObjectSprite.TILEF);
        out.level[6][5] = new GameObject(new Vector2(6, 5), GameObjectSprite.TILEO);
        out.level[7][5] = new GameObject(new Vector2(7, 5), GameObjectSprite.TILEU);
        out.level[8][5] = new GameObject(new Vector2(8, 5), GameObjectSprite.TILEN);
        out.level[9][5] = new GameObject(new Vector2(9, 5), GameObjectSprite.TILED);

        return out;
    }
}
