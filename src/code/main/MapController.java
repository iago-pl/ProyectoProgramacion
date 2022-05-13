package code.main;

import code.gameObjects.GameObjectSprite;
import code.gameObjects.PlayerEntity;
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

    public void loadMap() {
        //cargar mapa
        loading = true;
        snapShots.clear();
        currentMap = ReferenceController.mapReader.maps.get(0);

        for (int i = 0; i < currentMap.playground.level.length; i++) {
            for (int j = 0; j < currentMap.playground.level[0].length; j++) {
                if (currentMap.playground.level[i][j] != null) {
                    if (currentMap.playground.level[i][j].objectType == GameObjectSprite.PLAYER) {
                        ReferenceController.player = (PlayerEntity) currentMap.playground.level[i][j];
                        playerLastPos = ReferenceController.player.position;

                    }
                }
            }
        }
        //borrar esto
        ReferenceController.mapReader.maps.remove(0);
        if (ReferenceController.infoController != null) {
            ReferenceController.infoController.level++;
        }
        MapLayer temp = new MapLayer(currentMap.playground);
        snapShots.add(temp);

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
}
