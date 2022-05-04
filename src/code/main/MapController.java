package code.main;

import code.gameObjects.*;
import code.transform.Vector2;

/**
 *
 *
 * @author a21rebecanf
 */
public class MapController {

    public static GameObject[][] background = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    public static GameObject[][] gameObjects = new GameObject[GameFrame.TILE_SCREEN_SIZE.x][GameFrame.TILE_SCREEN_SIZE.y - 1];

    GameFrame gf;

    boolean loading;

    public MapController(GameFrame gf) {
        this.gf = gf;
        loading = true;
        loadMap();
    }

    public boolean isLoading() {
        return loading;
    }

    public void changeMap() {
        loading = true;
        gf.sleepThread(1000);

        //loadMap();
        loading = false;
    }

    private void loadMap() {
        //cargar mapa

        //borrar esto
        int temp = 5;

        gameObjects[temp][temp] = new Player(new Vector2(temp, temp));

        gameObjects[temp + 1][temp - 1] = new Box(new Vector2(temp + 1, temp - 1));
        gameObjects[temp + 1][temp] = new Flag(new Vector2(temp + 1, temp));
        gameObjects[temp + 1][temp + 1] = new Key(new Vector2(temp + 1, temp + 1));
        gameObjects[temp][temp + 1] = new Lock(new Vector2(temp, temp + 1));

        gameObjects[0][0] = new testLetter(new Vector2(0, 0));
        loading = false;
    }
}
