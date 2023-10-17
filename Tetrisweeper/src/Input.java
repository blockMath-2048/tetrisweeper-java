public class Input {
    public static boolean[] keyboard = new boolean[256];

    public static Vector3 mousePosition;

    public static float GetAxisRaw(String axis) {
        switch (axis) {
            case "Horizontal":
                if (keyboard['a'] && keyboard['d']) return 0;
                else if (keyboard['a']) return -1;
                else if (keyboard['d']) return 1;
                else return 0;
            case "Vertical":
                if (keyboard['w'] && keyboard['s']) return 0;
                else if (keyboard['s']) return -1;
                else if (keyboard['w']) return 1;
                else return 0;
            case "Rotation":
                if (keyboard['q'] && keyboard['e']) return 0;
                else if (keyboard['q']) return -1;
                else if (keyboard['e']) return 1;
                else return 0;
            case "Selection Vertical":
                if (keyboard['W'] && keyboard['S']) return 0;
                else if (keyboard['S']) return -1;
                else if (keyboard['W']) return 1;
                else return 0;
            case "Selection Horizontal":
                if (keyboard['A'] && keyboard['D']) return 0;
                else if (keyboard['A']) return -1;
                else if (keyboard['D']) return 1;
                else return 0;
            case "Cancel":
                if (keyboard['p']) return 1;
                else return 0;
            case "Mouse L":
                if (GetMouseButton(0)) return 1;
                else return 0;
            case "Mouse R":
                if (GetMouseButton(1)) return 1;
                else return 0;
            default:
                return 0;
        }
    }

    public static boolean GetMouseButton(int mouseButton) {
        return (mouseButton == 0)?keyboard['n']:keyboard['m'];
    }
}
