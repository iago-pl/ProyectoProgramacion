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
public class MapController {
    
    public Map currentMap = new Map(new MapLayer(), new MapLayer());
    
    ArrayList<MapLayer> snapShots = new ArrayList<>();
    
    boolean loading;
    
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
        currentMap = new Map(ReferenceController.mapReader.maps.get(0));
        
        for (int i = 0; i < currentMap.playground.level.length; i++) {
            for (int j = 0; j < currentMap.playground.level[0].length; j++) {
                if (currentMap.playground.level[i][j] != null) {
                    
                    switch (currentMap.playground.level[i][j].objectType) {
                        case PLAYER:
                            ReferenceController.player = (PlayerEntity) currentMap.playground.level[i][j];
                            currentMap.playground.level[i][j].setPosition(new Vector2(i, j));
                            playerLastPos = ReferenceController.player.position;
                            break;
                        case BOX:
                        case KEY:
                            currentMap.playground.level[i][j].setPosition(new Vector2(i, j));
                        
                    }
                }
            }
        }
        
        MapLayer temp = new MapLayer(currentMap.playground);
        snapShots.add(temp);
        
        loading = false;
    }
    
    public void loadMap() {
        //cargar mapa
        loading = true;
        
        if (ReferenceController.infoController != null) {
            ReferenceController.mapReader.maps.remove(0);
        }
        
        load();
        
        if (ReferenceController.infoController != null) {
            ReferenceController.dbController.insertLevelCompleted(ReferenceController.dbController.hashList.get(ReferenceController.infoController.getLevel()), ReferenceController.infoController.getStepsCurrentLevel() + 1);
            System.out.println("añadir numero pasos al total en bd");
            ReferenceController.infoController.increaseLevel();
            ReferenceController.infoController.resetCurrentStepCount();
        }
    }
    
    public void reloadMap() {
        //cargar mapa
        loading = true;
        
        load();
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
                        
                        currentMap.playground.level[i][j].setPosition(new Vector2(i, j));
                    }
                }
            }
        }
    }
}
