package code.main;

import code.gameObjects.*;
import code.transform.Vector2;
import java.util.ArrayList;

/**
 *
 *
 * @author a21rebecanf
 */
public class MapController {

    public Map background = new Map(null);
    public Map gameObjects = new Map(null);

    ArrayList<Map> snapShots = new ArrayList<>();

    boolean loading;

    Vector2 playerLastPos;

    public MapController() {
        loading = true;
        loadMap();

    }

    public boolean isLoading() {
        return loading;
    }

    public void changeMap() {
        loading = true;
        for (int i = 0; i < gameObjects.level.length; i++) {
            for (int j = 0; j < gameObjects.level[0].length; j++) {

                background.level[i][j] = null;
                gameObjects.level[i][j] = null;
            }
        }

        //loadMap();
    }

    private void loadMap() {
        //cargar mapa

        for (int i = 0; i < gameObjects.level.length; i++) {
            for (int j = 0; j < gameObjects.level[0].length; j++) {

                //background[i][j] = new Entity(new Vector2(i, j), GameObjectType.BOX, 1);
            }
        }

        //borrar esto
        PlayerEntity player = new PlayerEntity(new Vector2(0, 0));
        gameObjects.level[0][0] = player;
        ReferenceController.player = player;
        playerLastPos = player.position;

        gameObjects.level[5][5] = new KeyEntity(new Vector2(5, 5));
        gameObjects.level[6][6] = new Entity(new Vector2(6, 6), GameObjectType.BOX, 1);
        gameObjects.level[7][7] = new GameObject(new Vector2(7, 7), GameObjectType.LOCK, 1);
        gameObjects.level[8][8] = new GameObject(new Vector2(8, 8), GameObjectType.FLAG, 1);
        //

        snapShots.add(new Map(gameObjects));
        loading = false;
    }

    public void takeSnapshot() {

        if (playerLastPos.x != ReferenceController.player.position.x || playerLastPos.y != ReferenceController.player.position.y) {

            Map temp = new Map(gameObjects);

            snapShots.add(temp);

            playerLastPos = ReferenceController.player.position;
        }
    }

    public void loadSnapshot() {
        if (snapShots.size() > 1) {

            snapShots.remove(snapShots.size() - 1);
            gameObjects = new Map(snapShots.get(snapShots.size() - 1));
            for (int i = 0; i < gameObjects.level.length; i++) {
                for (int j = 0; j < gameObjects.level[0].length; j++) {
                    if (gameObjects.level[i][j] != null) {
                        gameObjects.level[i][j].position = new Vector2(i, j);
                    }
                }
            }
        }
    }

    public void clearSnapshot() {
        snapShots.clear();
    }
}
