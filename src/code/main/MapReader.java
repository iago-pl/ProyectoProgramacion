package code.main;

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

    public static ArrayList<Map> maps;

    public MapReader() {
        System.out.println(new File(getClass().getResource("/resources/maps").getPath()).list().length);
        loadMap();
    }

    private void loadMap() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/resources/maps/test.txt").getPath()));
            System.out.println(br.readLine());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
