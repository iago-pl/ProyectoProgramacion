package code.main;

import code.gameObjects.Entity;
import code.gameObjects.GameObject;
import code.gameObjects.GameObjectType;
import code.gameObjects.KeyEntity;
import code.gameObjects.PlayerEntity;
import code.transform.Vector2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a21iagopl
 */
public class MapReader {

    public ArrayList<Map> maps = new ArrayList<>();
    private MapLayer[] mapLayers = new MapLayer[2];
    private File[] files;

    public MapReader() {

        File dir = new File(getClass().getResource("/resources/maps").getPath());

        System.out.println("añadir mapas por defecto");

        files = dir.listFiles();

        for (int i = files.length - 1; i >= 0; i--) {
            try {
                loadMap(new BufferedReader(new FileReader(files[i])));
            } catch (Exception ex) {
                Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadMap(BufferedReader br) throws Exception {
        try {
            boolean hasPlayer = false;
            boolean hasFlag = false;

            String line = br.readLine();

            mapLayers[0] = new MapLayer();

            //Leer playground
            //Alto
            for (int i = 0; i < (GameFrame.TILE_SCREEN_SIZE.y - 1); i++) {

                if (i == 9) {

                } else {

                    if (line.length() != GameFrame.TILE_SCREEN_SIZE.x || line == null) {
                        System.out.println("Big Oof");
                        throw new Exception("Mapa de tamaño incorrecto");
                    }

                    //Ancho
                    for (int j = 0; j < line.length(); j++) {
                        if (!" ".equals(line.charAt(j) + "")) {
                            mapLayers[0].level[j][i] = convertToGameObject((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                            if (mapLayers[0].level[j][i] != null) {
                                if (mapLayers[0].level[j][i].objectType == GameObjectType.PLAYER) {
                                    if (!hasPlayer) {
                                        hasPlayer = true;
                                        ReferenceController.player = (PlayerEntity) mapLayers[0].level[j][i];
                                        
                                    } else {
                                        mapLayers[0].level[j][i] = null;
                                    }

                                }else if (mapLayers[0].level[j][i].objectType == GameObjectType.FLAG) {
                                    hasFlag = true;
                                }
                            }

                        }
                    }
                    line = br.readLine();
                }
            }

            //descartar espaciado
            line = br.readLine();
            mapLayers[1] = new MapLayer();

            //Leer background
            //Alto
            for (int i = 0; i < (GameFrame.TILE_SCREEN_SIZE.y - 1); i++) {

                if (i == 9) {

                } else {

                    if (line.length() != GameFrame.TILE_SCREEN_SIZE.x || line == null) {
                        System.out.println("Big Oof");
                        throw new Exception("Mapa de tamaño incorrecto");
                    }

                    //Ancho
                    for (int j = 0; j < line.length(); j++) {
                        if (!" ".equals(line.charAt(j) + "")) {
                            mapLayers[1].level[j][i] = convertToBackground((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                        }
                    }
                    line = br.readLine();
                }
            }
            //AÑADIR mapa
            if (hasPlayer && hasFlag) {
                Map tempMap = new Map(mapLayers[1], mapLayers[0]);
                maps.add(tempMap);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private GameObject convertToGameObject(String in, Vector2 pos) {

        switch (in) {
            case "P":
                return new PlayerEntity(pos);
            case "K":
                return new KeyEntity(pos);
            case "B":
                return new Entity(pos, GameObjectType.BOX);
            case "F":
                return new GameObject(pos, GameObjectType.FLAG, 1);
            case "L":
                return new GameObject(pos, GameObjectType.LOCK, 1);
            case "W":
                return new GameObject(pos, GameObjectType.WALL, 1);
            default:
                return null;
        }
    }

    private GameObject convertToBackground(String in, Vector2 pos) {

        switch (in) {
            case "1":
                System.out.println("terminar");
                return new Entity(pos, GameObjectType.TILE);
            default:
                return null;
        }
    }

}
