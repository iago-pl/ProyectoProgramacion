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

    public static ArrayList<MapLayer> mapLayers;
    private File[] files;

    public MapReader() {

        File dir = new File(getClass().getResource("/resources/maps").getPath());

        System.out.println("añadir mapas por defecto");

        files = dir.listFiles();

        for (int i = files.length - 1; i >= 0; i--) {
            try {
                loadMap(i);
            } catch (Exception ex) {
                Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadMap(int i) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(files[i]));
            String line = br.readLine();
            MapLayer tempMap = new MapLayer();

            //Alto
            for (int j = 0; j < GameFrame.TILE_SCREEN_SIZE.y - 1; j++) {
                if (line.length() != GameFrame.TILE_SCREEN_SIZE.x || line == null) {
                    System.out.println("Big Oof");
                    throw new Exception("Mapa de tamaño incorrecto");
                }

                //Ancho
                for (int k = 0; k < line.length(); k++) {
                    if (!" ".equals(line.charAt(k) + "")) {
                        tempMap.level[k][j] = convertToGameObject((line.charAt(k) + "").toUpperCase(), new Vector2(k, j));
                    }
                }
                line = br.readLine();
            }
            
            System.out.println("arreglar esto");
            //maps.add(tempMap);

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
            default:
                return null;
        }
    }

}
