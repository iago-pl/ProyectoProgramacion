package code.Map;

/**
 *
 * @author a21iagopl
 */
public class Map {

    protected MapLayer background;
    protected MapLayer playground;

    public Map(MapLayer background, MapLayer playground) {
        this.background = background;
        this.playground = playground;
    }

    public Map(Map temp) {
        background = new MapLayer(temp.background);
        playground = new MapLayer(temp.playground);
    }

    /**
     * @return the background
     */
    public MapLayer getBackground() {
        return background;
    }

    /**
     * @return the playground
     */
    public MapLayer getPlayground() {
        return playground;
    }
}
