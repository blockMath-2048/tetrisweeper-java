public class BoardTilemap extends GameScript {
    public BoardTilemap globalTilemap;

    public MeshFilter meshFilter;

    public MeshRenderer meshRenderer;

    private Mesh mesh;

    public Material graphics;

    public Material graphicsLoss;

    public Vector2Int boardSize;

    public Vector2 tileSize;

    public boolean playerAlive;

    public boolean lastPlayerAlive;

    public boolean paused;

    public byte[][] boardTiles;

    public void Start() {
        meshFilter = GetComponent < MeshFilter > ();
        meshRenderer = GetComponent < MeshRenderer > ();
        int num = boardSize.x * boardSize.y;
        Vector3[] array = new Vector3[num * 4];
        Vector2[] array2 = new Vector2[num * 4];
        int[] array3 = new int[num * 6];
        for (int i = 0; i < boardSize.y; i++) {
            for (int j = 0; j < boardSize.x; j++) {
                int num2 = i * boardSize.x + j;
                array[num2 * 4] = new Vector3((float) boardSize.x * tileSize.x * -0.5f + (float) j * tileSize.x, (float) boardSize.y * tileSize.y * -0.5f + (float) i * tileSize.y, 0f);
                array[num2 * 4 + 1] = new Vector3((float) boardSize.x * tileSize.x * -0.5f + (float) (j + 1) * tileSize.x, (float) boardSize.y * tileSize.y * -0.5f + (float) i * tileSize.y, 0f);
                array[num2 * 4 + 2] = new Vector3((float) boardSize.x * tileSize.x * -0.5f + (float) j * tileSize.x, (float) boardSize.y * tileSize.y * -0.5f + (float) (i + 1) * tileSize.y, 0f);
                array[num2 * 4 + 3] = new Vector3((float) boardSize.x * tileSize.x * -0.5f + (float) (j + 1) * tileSize.x, (float) boardSize.y * tileSize.y * -0.5f + (float) (i + 1) * tileSize.y, 0f);
                array2[num2 * 4] = new Vector2(0f, 0f);
                array2[num2 * 4 + 1] = new Vector2(0f, 0f);
                array2[num2 * 4 + 2] = new Vector2(0f, 0f);
                array2[num2 * 4 + 3] = new Vector2(0f, 0f);
                array3[num2 * 6] = num2 * 4;
                array3[num2 * 6 + 1] = num2 * 4 + 2;
                array3[num2 * 6 + 2] = num2 * 4 + 1;
                array3[num2 * 6 + 3] = num2 * 4 + 1;
                array3[num2 * 6 + 4] = num2 * 4 + 2;
                array3[num2 * 6 + 5] = num2 * 4 + 3;
            }
        }
        mesh = new Mesh();
        mesh.vertices = array;
        mesh.triangles = array3;
        mesh.uv = array2;
        meshFilter.mesh = mesh;
        playerAlive = true;
        boardTiles = new byte[boardSize.y][];
        for (int k = 0; k < boardSize.y; k++) {
            boardTiles[k] = new byte[boardSize.x];
            for (int l = 0; l < boardSize.x; l++) {
                boardTiles[k][l] = 127;
            }
        }
    }

    public void Update(float deltaTime, Main main) {
        if (globalTilemap != null) {
            playerAlive = globalTilemap.playerAlive;
        }
        Vector2[] array = new Vector2[boardSize.x * boardSize.y * 4];
        for (int i = 0; i < boardSize.y; i++) {
            for (int j = 0; j < boardSize.x; j++) {
                int num = i * boardSize.x + j;
                float num2 = 0f;
                byte b = boardTiles[i][j];
                if (i >= boardSize.y - 5 && b == 127) {
                    b = 95;
                }
                Vector2 vector = new Vector2((float) (b % 16) / 16f, (float) (16 - b / 16) / 16f);
                array[num * 4] = new Vector2(0f + num2, -0.0625f + num2) + vector;
                array[num * 4 + 1] = new Vector2(0.0625f - num2, -0.0625f + num2) + vector;
                array[num * 4 + 2] = new Vector2(0f + num2, 0f - num2) + vector;
                array[num * 4 + 3] = new Vector2(0.0625f - num2, 0f - num2) + vector;
            }
        }
        mesh.uv = array;
        meshFilter.mesh = mesh;
        if (playerAlive) {
            meshRenderer.material = graphics;
        } else {
            meshRenderer.material = graphicsLoss;
        }
    }
}
