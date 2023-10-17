public class Vector3 {
    public float x, y, z;

    Vector3(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public Vector3 mul(float f) {
        return new Vector3(x * f, y * f, z * f);
    }
}
