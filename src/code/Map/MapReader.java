package code.map;

import code.main.Hasher;
import code.exceptions.EmptyMapException;
import code.exceptions.MultiplePlayersException;
import code.exceptions.NoMapEssentialsException;
import code.exceptions.WrongMapSizeException;
import code.gameObjects.*;
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
 * @author Rebeca Noya y Iago Pena
 */
public class MapReader {

    private static final ArrayList<Map> maps = new ArrayList<>();
    private static final MapLayer[] mapLayers = new MapLayer[2];
    private static File[] files;

    public MapReader() {

        File dir = new File(getClass().getResource("/resources/maps").getPath());

        if (dir.listFiles().length == 0) {
            maps.add(new DefaultMap());
        } else {

            files = dir.listFiles();
            Arrays.sort(files);

            for (File file : files) {
                try {
                    loadMap(new BufferedReader(new FileReader(file)));
                    System.out.println(file.getName() + " cargado correctamente");
                    ReferenceController.dbController.addHash(Hasher.getHash(file));
                } catch (Exception ex) {
                    Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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

            if (line == null) {
                throw new EmptyMapException();
            }
            //Leer playground
            //Alto
            for (int i = 0; i < (GameFrame.TILE_SCREEN_SIZE.y - 1); i++) {

                if (line == null) {
                    throw new WrongMapSizeException("Only " + i + " lines found 19 required");
                }

                if (line.length() != GameFrame.TILE_SCREEN_SIZE.x) {
                    throw new WrongMapSizeException(i + 1);
                }

                //Ancho
                for (int j = 0; j < line.length(); j++) {
                    if (!" ".equals(line.charAt(j) + "")) {
                        mapLayers[0].getLevel()[j][i] = convertToGameObject((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                        if (mapLayers[0].getLevel()[j][i] != null) {
                            if (mapLayers[0].getLevel()[j][i].getObjectType() == GameObjectSprite.PLAYER) {
                                if (!hasPlayer) {
                                    hasPlayer = true;
                                } else {
                                    throw new MultiplePlayersException(new Vector2(j, i));
                                }

                            } else if (mapLayers[0].getLevel()[j][i].getObjectType() == GameObjectSprite.FLAG) {
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
                if (line == null) {
                    throw new WrongMapSizeException("Only " + (i + 10) + " lines found 19 required");
                }
                if (line.length() != GameFrame.TILE_SCREEN_SIZE.x) {
                    throw new WrongMapSizeException(i + 11);
                }

                //Ancho
                for (int j = 0; j < line.length(); j++) {
                    if (!" ".equals(line.charAt(j) + "")) {
                        mapLayers[1].getLevel()[j][i] = convertToBackground((line.charAt(j) + "").toUpperCase(), new Vector2(j, i));
                    }
                }
                line = br.readLine();

            }
            //AÑADIR mapa
            if (hasPlayer && hasFlag) {
                Map tempMap = new Map(mapLayers[1], mapLayers[0]);
                maps.add(tempMap);
            } else {
                throw new NoMapEssentialsException();
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
                return new GameObject(pos, GameObjectSprite.FLAG);
            case "L":
                return new GameObject(pos, GameObjectSprite.LOCK);
            case "W":
                return new GameObject(pos, GameObjectSprite.WALL);
            case "M":
                return new GameObject(pos, GameObjectSprite.MONSTER);
            default:
                return null;
        }
    }

    private GameObject convertToBackground(String in, Vector2 pos) {

        switch (in) {
            case ".":
                return new GameObject(pos, GameObjectSprite.TILE);
            case "W":
                return new GameObject(pos, GameObjectSprite.TILEW);
            case "A":
                return new GameObject(pos, GameObjectSprite.TILEA);
            case "S":
                return new GameObject(pos, GameObjectSprite.TILES);
            case "D":
                return new GameObject(pos, GameObjectSprite.TILED);
            case "R":
                return new GameObject(pos, GameObjectSprite.TILER);
            case "F":
                return new GameObject(pos, GameObjectSprite.TILEF);
            case "M":
                return new GameObject(pos, GameObjectSprite.TILEM);
            case "N":
                return new GameObject(pos, GameObjectSprite.TILEN);
            case "O":
                return new GameObject(pos, GameObjectSprite.TILEO);
            case "P":
                return new GameObject(pos, GameObjectSprite.TILEP);
            case "U":
                return new GameObject(pos, GameObjectSprite.TILEU);
            default:
                return null;
        }
    }

    public void removeMap() {
        maps.remove(0);
    }

    /**
     * @return the maps
     */
    public ArrayList<Map> getMaps() {
        return maps;
    }

}
