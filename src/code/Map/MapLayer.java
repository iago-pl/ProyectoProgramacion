package code.map;

import code.gameObjects.GameObject;
import code.main.GameFrame;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class MapLayer {

    protected final GameObject[][] level = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    public MapLayer() {

    }

    public MapLayer(MapLayer copy) {

        if (copy != null) {
            for (int i = 0; i < level.length; i++) {
                for (int j = 0; j < level[0].length; j++) {
                    level[i][j] = copy.level[i][j];
                }
            }
        }
    }

    /**
     * @return the level
     */
    public GameObject[][] getLevel() {
        return level;
    }

}
