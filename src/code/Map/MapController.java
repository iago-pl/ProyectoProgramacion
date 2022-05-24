package code.Map;

import code.gameObjects.PlayerEntity;
import code.main.ReferenceController;
import code.transform.Vector2;
import java.util.ArrayList;

/**
 *
 *
 * @author a21rebecanf
 */
public final class MapController {

    private Map currentMap = new Map(new MapLayer(), new MapLayer());

    private final ArrayList<MapLayer> snapShots = new ArrayList<>();

    private boolean loading;

    private Vector2 playerLastPos;

    public MapController() {
        loading = true;
        loadMap();
    }

    public boolean isLoading() {
        return loading;
    }

    private void load() {

        snapShots.clear();
        currentMap = new Map(ReferenceController.mapReader.getMaps().get(0));

        for (int i = 0; i < currentMap.getPlayground().getLevel().length; i++) {
            for (int j = 0; j < currentMap.getPlayground().getLevel()[0].length; j++) {
                if (currentMap.getPlayground().getLevel()[i][j] != null) {

                    switch (currentMap.getPlayground().getLevel()[i][j].getObjectType()) {
                        case PLAYER:
                            ReferenceController.player = (PlayerEntity) currentMap.getPlayground().getLevel()[i][j];
                            currentMap.getPlayground().getLevel()[i][j].setPosition(new Vector2(i, j));
                            playerLastPos = ReferenceController.player.getPosition();
                            break;
                        case BOX:
                        case KEY:
                            currentMap.getPlayground().getLevel()[i][j].setPosition(new Vector2(i, j));

                    }
                }
            }
        }

        MapLayer temp = new MapLayer(currentMap.getPlayground());
        snapShots.add(temp);

        loading = false;
    }

    public void loadMap() {
        //cargar mapa
        loading = true;

        if (ReferenceController.infoController != null) {
            ReferenceController.mapReader.removeMap();
            ReferenceController.dbController.insertLevelCompleted(ReferenceController.infoController.getLevel(), ReferenceController.infoController.getStepsCurrentLevel());
            ReferenceController.infoController.resetCurrentStepCount();
            ReferenceController.infoController.increaseLevel();
        }

        load();
    }

    public void reloadMap() {
        //cargar mapa
        loading = true;

        load();
    }

    public void takeSnapshot() {

        if (playerLastPos.x != ReferenceController.player.getPosition().x || playerLastPos.y != ReferenceController.player.getPosition().y) {

            MapLayer temp = new MapLayer(currentMap.getPlayground());

            snapShots.add(temp);

            playerLastPos = ReferenceController.player.getPosition();

        }
    }

    public void loadSnapshot() {
        if (snapShots.size() > 1) {
            ReferenceController.audioController.play(1);
            snapShots.remove(snapShots.size() - 1);
            currentMap.playground = new MapLayer(snapShots.get(snapShots.size() - 1));
            for (int i = 0; i < currentMap.getPlayground().getLevel().length; i++) {
                for (int j = 0; j < currentMap.getPlayground().getLevel()[0].length; j++) {
                    if (currentMap.getPlayground().getLevel()[i][j] != null) {

                        currentMap.getPlayground().getLevel()[i][j].setPosition(new Vector2(i, j));
                    }
                }
            }
        }
    }

    /**
     * @return the currentMap
     */
    public Map getCurrentMap() {
        return currentMap;
    }
}
