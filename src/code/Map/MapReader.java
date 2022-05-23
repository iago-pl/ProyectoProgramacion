package code.Map;

import code.gameObjects.Entity;
import code.gameObjects.GameObject;
import code.gameObjects.GameObjectSprite;
import code.gameObjects.Hasher;
import code.gameObjects.KeyEntity;
import code.gameObjects.PlayerEntity;
import code.main.GameFrame;
import code.main.ReferenceController;
import code.transform.Vector2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        if (dir.listFiles().length == 0) {
            maps.add(new DefaultMap());
        } else {

            files = dir.listFiles();
            Arrays.sort(files);

            for (File file : files) {
                try {
                    System.out.println("cargando " + file.getName());
                    loadMap(new BufferedReader(new FileReader(file)));
                    ReferenceController.dbController.hashList.add(Hasher.getHash(file));
                } catch (Exception ex) {
                    Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            ReferenceController.dbController.insertAllLevels();

            if (maps.isEmpty()) {
                maps.add(new DefaultMap());
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

                if (line == null) {
                    throw new Exception();
                }
                if (line.length() != GameFrame.TILE_SCREEN_SIZE.x) {
                    throw new Exception();
                }

                //Ancho
                for (int j = 0; j < line.length(); j++) {
                    if (!" ".equals(line.charAt(j) + "")) {
                        mapLayers[0].level[j][i] = convertToGameObject((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                        if (mapLayers[0].level[j][i] != null) {
                            if (mapLayers[0].level[j][i].objectType == GameObjectSprite.PLAYER) {
                                if (!hasPlayer) {
                                    hasPlayer = true;
                                } else {
                                    throw new Exception();
                                }

                            } else if (mapLayers[0].level[j][i].objectType == GameObjectSprite.FLAG) {
                                hasFlag = true;
                            }
                        }

                    }
                }
                line = br.readLine();

            }

            //descartar espaciado
            line = br.readLine();
            mapLayers[1] = new MapLayer();

            //Leer background
            //Alto
            for (int i = 0; i < (GameFrame.TILE_SCREEN_SIZE.y - 1); i++) {

                if (line.length() != GameFrame.TILE_SCREEN_SIZE.x || line == null) {
                    throw new Exception();
                }

                //Ancho
                for (int j = 0; j < line.length(); j++) {
                    if (!" ".equals(line.charAt(j) + "")) {
                        mapLayers[1].level[j][i] = convertToBackground((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                    }
                }
                line = br.readLine();

            }
            //AÑADIR mapa
            if (hasPlayer && hasFlag) {
                Map tempMap = new Map(mapLayers[1], mapLayers[0]);
                maps.add(tempMap);
                System.out.println("Cargado correctamente");
            } else {
                throw new Exception();
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
                return new Entity(pos, GameObjectSprite.BOX);
            case "F":
                return new GameObject(pos, GameObjectSprite.FLAG, 1);
            case "L":
                return new GameObject(pos, GameObjectSprite.LOCK, 1);
            case "W":
                return new GameObject(pos, GameObjectSprite.WALL, 1);
            case "M":
                return new GameObject(pos, GameObjectSprite.MONSTER, 1);
            default:
                return null;
        }
    }

    private GameObject convertToBackground(String in, Vector2 pos) {

        switch (in) {
            case ".":
                return new GameObject(pos, GameObjectSprite.TILE, 1);
            case "W":
                return new GameObject(pos, GameObjectSprite.TILEW, 1);
            case "A":
                return new GameObject(pos, GameObjectSprite.TILEA, 1);
            case "S":
                return new GameObject(pos, GameObjectSprite.TILES, 1);
            case "D":
                return new GameObject(pos, GameObjectSprite.TILED, 1);
            case "R":
                return new GameObject(pos, GameObjectSprite.TILER, 1);
            case "F":
                return new GameObject(pos, GameObjectSprite.TILEF, 1);
            case "M":
                return new GameObject(pos, GameObjectSprite.TILEM, 1);
            case "N":
                return new GameObject(pos, GameObjectSprite.TILEN, 1);
            case "O":
                return new GameObject(pos, GameObjectSprite.TILEO, 1);
            case "P":
                return new GameObject(pos, GameObjectSprite.TILEP, 1);
            case "U":
                return new GameObject(pos, GameObjectSprite.TILEU, 1);
            default:
                return null;
        }
    }

}