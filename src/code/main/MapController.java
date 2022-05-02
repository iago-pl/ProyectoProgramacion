
package code.main;

import code.gameObjects.GameObject;

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
    }
}
