package code.main;

import code.gameObjects.*;
import code.transform.Vector2;

/**
 *
 *
 * @author a21rebecanf
 */
public class MapController {

    public GameObject[][] background = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    public GameObject[][] gameObjects = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    boolean loading;

    public MapController() {
        loading = true;
        loadMap();
    }

    public boolean isLoading() {
        return loading;
    }

    public void changeMap() {
        loading = true;
        //gf.sleepThread(1000);
        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects[0].length; j++) {

                background[i][j] = null;
                gameObjects[i][j] = null;
                //gf.sleepThread(10);
            }
        }

        //loadMap();
    }

    private void loadMap() {
        //cargar mapa

        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects[0].length; j++) {

                //background[i][j] = new Entity(new Vector2(i, j), GameObjectType.BOX, 1);
            }
        }
        //borrar esto
        Entity player = new Entity(new Vector2(0, 0), GameObjectType.PLAYER, 1);
        gameObjects[0][0] = player;
        ReferenceController.player = player;

        gameObjects[5][5] = new Entity(new Vector2(5, 5), GameObjectType.KEY, 1);
        gameObjects[6][6] = new Entity(new Vector2(6, 6), GameObjectType.BOX, 1);
        gameObjects[7][7] = new Entity(new Vector2(7, 7), GameObjectType.LOCK, 1);
        loading = false;
    }
}
