package code.main;

import code.transform.Vector2;
import javax.swing.JPanel;

/**
 *
 * @author a21iagopl
 */
public class GameFrame extends JPanel {

    public final int baseTileSize = 24;
    public final int scale = 4;

    public final int tileSize = (baseTileSize * scale);
    public final Vector2 tileScreenSize = new Vector2(10, 7);

    public final Vector2 screenSize = new Vector2(tileScreenSize.x * tileSize, tileScreenSize.y * tileSize);

}
