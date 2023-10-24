import processing.core.PApplet;
import processing.core.PImage;

import java.util.Locale;

public class BoardTilemap extends GameScript {
    public BoardTilemap globalTilemap;


    public PImage[] graphicsAlive;

    public PImage[] graphicsLoss;

    public PImage[] graphics;

    public Vector2Int boardSize;

    public Vector2 tileSize;

    public boolean playerAlive;

    public boolean lastPlayerAlive;

    public boolean paused;

    public short[][] boardTiles;

    public void Init(Main main) {
        graphicsAlive = new PImage[256];
        graphicsLoss = new PImage[256];
        for (int i = 0; i < 256; i++) {
            graphicsAlive[i] = main.loadImage(String.format(Locale.ENGLISH, "graphics/tile%03d.png", i));
            graphicsLoss[i] = main.loadImage(String.format(Locale.ENGLISH, "graphics-failure/tile%03d.png", i));
        }
    }

    public void Start() {
        int num = boardSize.x * boardSize.y;

        playerAlive = true;
        boardTiles = new short[boardSize.y][];
        for (int k = 0; k < boardSize.y; k++) {
            boardTiles[k] = new short[boardSize.x];
            for (int l = 0; l < boardSize.x; l++) {
                boardTiles[k][l] = 127;
            }
        }
    }

    public void Update(float deltaTime, Main main) {
        if (globalTilemap != null) {
            playerAlive = globalTilemap.playerAlive;
        }

        if (playerAlive) {
            graphics = graphicsAlive;
        } else {
            graphics = graphicsLoss;
        }

        for (int i = 0; i < boardSize.y; i++) {
            for (int j = 0; j < boardSize.x; j++) {
                short b = boardTiles[i][j];
                if (i >= boardSize.y - 5 && b == 127) {
                    b = 95;
                }
                main.image(graphics[((int)b + 256) & 0xFF], j * tileSize.x + transform.x, (boardSize.y - i) * tileSize.y + transform.y, tileSize.x, tileSize.y);
            }
        }
    }
}
