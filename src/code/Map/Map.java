
package code.Map;
/**
 *
 * @author a21iagopl
 */
public class Map {
    public MapLayer background;
    public MapLayer playground;

    public Map(MapLayer background, MapLayer playground) {
        this.background = background;
        this.playground = playground;
    }
    
    public Map(Map temp){
        background = new MapLayer(temp.background);
        playground = new MapLayer(temp.playground);
    }
}
