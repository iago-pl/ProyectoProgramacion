package code.main;

import code.gameObjects.GameObject;
import code.gameObjects.Player;
import code.transform.Vector2;

/**
 * a
 *
 * @author a21rebecanf
 */
public class MapController {

    public static GameObject[][] background = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y];

    public static GameObject[][] gameObjets = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y];

    public MapController() {
        loadMap();
    }

    public void loadMap() {
        //cargar mapa
        
        int temp = 5;
        
        gameObjets[temp][temp] = new Player(new Vector2(temp, temp));

        System.out.println(gameObjets[0][0]);
    }
}
