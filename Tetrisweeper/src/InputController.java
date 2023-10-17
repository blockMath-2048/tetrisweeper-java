import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class InputController extends GameScript {
    public BoardController board;

    public float[] repDelay = new float[]{
        0.25f,
                0.25f,
                0.1f,
                0.5f,
                0.5f,
                Float.POSITIVE_INFINITY,
                0.25f,
                0.25f,
                0.25f,
                0.25f
    };

    public float[] repFreq = new float[]{
        0.1f,
                0.1f,
                0.1f,
                0.25f,
                0.25f,
                Float.POSITIVE_INFINITY,
                0.1f,
                0.1f,
                0.1f,
                0.1f
    };

    public int left;

    public int right;

    public int down;

    public int rotLeft;

    public int rotRight;

    public int hold;

    public int selUp;

    public int selDown;

    public int selLeft;

    public int selRight;

    public int reveal;

    public int flag;

    public int pause;

    public Vector2Int selectionPos;

    public int[] pressed;

    public float[] held;

    public float[] lastHeld;

    public boolean mouseControl = true;

    public void Start()
    {
        held = new float[10];
        lastHeld = new float[10];
        pressed = new int[10];
        selectionPos = new Vector2Int(5, 0);
    }

    private void UpdateBlockControls(float deltaTime)
    {
        float axisRaw = Input.GetAxisRaw("Horizontal");
        float axisRaw2 = Input.GetAxisRaw("Vertical");
        float axisRaw3 = Input.GetAxisRaw("Rotation");
        float axisRaw4 = Input.GetAxisRaw("Selection Horizontal");
        float axisRaw5 = Input.GetAxisRaw("Selection Vertical");
        for (int i = 0; i < 10; i++)
        {
            lastHeld[i] = held[i];
            pressed[i] = 0;
        }
        if (axisRaw < 0f)
        {
            held[0] += deltaTime;
        }
        else
        {
            held[0] = 0f;
        }
        if (axisRaw > 0f)
        {
            held[1] += deltaTime;
        }
        else
        {
            held[1] = 0f;
        }
        if (axisRaw2 < 0f)
        {
            held[2] += deltaTime;
        }
        else
        {
            held[2] = 0f;
        }
        if (axisRaw3 < 0f)
        {
            held[3] += deltaTime;
        }
        else
        {
            held[3] = 0f;
        }
        if (axisRaw3 > 0f)
        {
            held[4] += deltaTime;
        }
        else
        {
            held[4] = 0f;
        }
        if (axisRaw2 > 0f)
        {
            held[5] += deltaTime;
        }
        else
        {
            held[5] = 0f;
        }
        if (axisRaw5 > 0f)
        {
            held[6] += deltaTime;
        }
        else
        {
            held[6] = 0f;
        }
        if (axisRaw5 < 0f)
        {
            held[7] += deltaTime;
        }
        else
        {
            held[7] = 0f;
        }
        if (axisRaw4 < 0f)
        {
            held[8] += deltaTime;
        }
        else
        {
            held[8] = 0f;
        }
        if (axisRaw4 > 0f)
        {
            held[9] += deltaTime;
        }
        else
        {
            held[9] = 0f;
        }
        for (int j = 0; j < 10; j++)
        {
            if (lastHeld[j] <= 0f && held[j] > 0f)
            {
                pressed[j]++;
            }
            else if (lastHeld[j] < repDelay[j] && held[j] >= repDelay[j])
            {
                pressed[j]++;
            }
            else if (held[j] > repDelay[j])
            {
                float num = lastHeld[j] - repDelay[j];
                float num2 = held[j] - repDelay[j];
                while (num >= 0f)
                {
                    num -= repFreq[j];
                    num2 -= repFreq[j];
                }
                if (num < 0f && num2 >= 0f)
                {
                    pressed[j]++;
                }
            }
        }
        left += pressed[0];
        right += pressed[1];
        down += pressed[2];
        rotLeft += pressed[3];
        rotRight += pressed[4];
        hold += pressed[5];
        selUp += pressed[6];
        selDown += pressed[7];
        selLeft += pressed[8];
        selRight += pressed[9];
    }

    public void clear()
    {
        left = (right = (down = (rotLeft = (rotRight = (hold = (reveal = (flag = (pause = (selUp = (selDown = (selLeft = (selRight = 0))))))))))));
    }

    public void Update(float deltaTime, Main main)
    {
        UpdateBlockControls(deltaTime);
        if (Input.GetAxisRaw("Cancel") > 0f)
        {
            pause = 1;
        }
        if (mouseControl)
        {
            Vector3 vector = Input.mousePosition;
            Vector2 vector2 = new Vector2((vector.x - board.tilemap.transform.x) / board.tilemap.tileSize.x + (float)(board.tilemap.boardSize.x / 2), (vector.y - board.tilemap.transform.y) / board.tilemap.tileSize.y + (float)(board.tilemap.boardSize.y / 2) + 0.5f);
            selectionPos = new Vector2Int((int)vector2.x, (int)vector2.y);
            reveal = (Input.GetMouseButton(0) ? 1 : 0);
            flag = (Input.GetMouseButton(1) ? 1 : 0);
            if (selectionPos.x < 0 || selectionPos.x >= board.tilemap.boardSize.x || selectionPos.y < 0 || selectionPos.y >= board.tilemap.boardSize.y)
            {
                selectionPos = new Vector2Int(-999, -999);
            }
            return;
        }
        if (selUp > 0)
        {
            selUp--;
            selectionPos = Vector2Int.add(selectionPos, new Vector2Int(0, 1));
        }
        if (selDown > 0)
        {
            selDown--;
            selectionPos = Vector2Int.add(selectionPos, new Vector2Int(0, -1));
        }
        if (selLeft > 0)
        {
            selLeft--;
            selectionPos = Vector2Int.add(selectionPos, new Vector2Int(-1, 0));
        }
        if (selRight > 0)
        {
            selRight--;
            selectionPos = Vector2Int.add(selectionPos, new Vector2Int(1, 0));
        }
        if (selectionPos.x < 0)
        {
            selectionPos.x = 0;
        }
        if (selectionPos.x >= board.tilemap.boardSize.x)
        {
            selectionPos.x = board.tilemap.boardSize.x - 1;
        }
        if (selectionPos.y < 0)
        {
            selectionPos.y = 0;
        }
        if (selectionPos.y >= board.tilemap.boardSize.y)
        {
            selectionPos.y = board.tilemap.boardSize.y - 1;
        }
        reveal = ((Input.GetAxisRaw("Mouse L") > 0f) ? 1 : 0);
        flag = ((Input.GetAxisRaw("Mouse R") > 0f) ? 1 : 0);
    }
}
