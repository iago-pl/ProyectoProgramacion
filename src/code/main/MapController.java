
package code.main;

import code.gameObjects.GameObject;
import code.gameObjects.Player;
import code.transform.Vector2;

/**
 *a
 * @author a21rebecanf
 */
public class MapController {
    
    public static GameObject[][] background = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y];
    
    public static GameObject[][] gameObjets = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y];
    
    public MapController(){
        loadMap();
    }
    
    public void loadMap(){
        //cargar mapa
        gameObjets[0][0] = new Player(new Vector2(0, 0));
        
        System.out.println(gameObjets[0][0]);
    }
}
