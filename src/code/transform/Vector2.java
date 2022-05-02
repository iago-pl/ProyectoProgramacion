package code.transform;

/**
 *
 * @author a21rebecanf
 */
public class Vector2 {

    public int x;
    public int y;

    public static Vector2 add(Vector2 a, Vector2 b) {

        return new Vector2(a.x + b.x, a.y + b.y);

    }

    public static Vector2 sub(Vector2 a, Vector2 b) {

        return new Vector2(a.x - b.x, a.y - b.y);

    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
