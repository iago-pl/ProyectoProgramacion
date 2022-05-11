package code.main;

import code.gameObjects.GameObjectType;
import code.transform.Vector2;
import java.util.ArrayList;

/**
 *
 *
 * @author a21rebecanf
 */
public class MapController {

    public Map currentMap = new Map(new MapLayer(), new MapLayer());

    ArrayList<MapLayer> snapShots = new ArrayList<>();

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
        for (int i = 0; i < currentMap.playground.level.length; i++) {
            for (int j = 0; j < currentMap.playground.level[0].length; j++) {

                currentMap.background.level[i][j] = null;
                currentMap.playground.level[i][j] = null;
            }
        }

        //loadMap();
    }

    private void loadMap() {
        //cargar mapa

        //borrar esto
        currentMap = ReferenceController.mapReader.maps.get(0);
        playerLastPos = ReferenceController.player.position;
        ReferenceController.mapReader.maps.remove(0);

        /*PlayerEntity player = new PlayerEntity(new Vector2(0, 0));
        currentMap.playground.level[0][0] = player;
        ReferenceController.player = player;
        playerLastPos = player.position;

        currentMap.playground.level[5][5] = new KeyEntity(new Vector2(5, 5));
        currentMap.playground.level[6][6] = new Entity(new Vector2(6, 6), GameObjectType.BOX);
        currentMap.playground.level[7][7] = new GameObject(new Vector2(7, 7), GameObjectType.LOCK, 1);
        currentMap.playground.level[8][8] = new GameObject(new Vector2(8, 8), GameObjectType.FLAG, 1);
        //

        snapShots.add(new MapLayer(currentMap.playground));*/
        loading = false;
    }

    public void takeSnapshot() {

        if (playerLastPos.x != ReferenceController.player.position.x || playerLastPos.y != ReferenceController.player.position.y) {

            MapLayer temp = new MapLayer(currentMap.playground);

            snapShots.add(temp);

            playerLastPos = ReferenceController.player.position;
        }
    }

    public void loadSnapshot() {
        if (snapShots.size() > 1) {
            ReferenceController.audioController.play(1);
            snapShots.remove(snapShots.size() - 1);
            currentMap.playground = new MapLayer(snapShots.get(snapShots.size() - 1));
            for (int i = 0; i < currentMap.playground.level.length; i++) {
                for (int j = 0; j < currentMap.playground.level[0].length; j++) {
                    if (currentMap.playground.level[i][j] != null) {
                        currentMap.playground.level[i][j].position = new Vector2(i, j);
                    }
                }
            }
        }
    }

    public void clearSnapshots() {
        snapShots.clear();
    }
}
