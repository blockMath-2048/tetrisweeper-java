public class Vector2Int {
    public int x, y;

    Vector2Int(int _x, int _y) {
        x =_x;
        y =_y;
    }

    public static Vector2Int add(Vector2Int a, Vector2Int b) {
        return new Vector2Int(a.x + b.x, a.y + b.y);
    }
    public static Vector2Int sub(Vector2Int a, Vector2Int b) {
        return new Vector2Int(a.x - b.x, a.y - b.y);
    }
}
