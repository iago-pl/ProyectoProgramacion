package code.main;

import code.gameObjects.GameObject;

/**
 *
 * @author a21iagopl
 */
public class Map {

    public GameObject[][] level = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    public Map(Map copy) {

        if (copy != null) {
            for (int i = 0; i < level.length; i++) {
                for (int j = 0; j < level[0].length; j++) {
                    level[i][j] = copy.level[i][j];
                }
            }
        }
    }

}
