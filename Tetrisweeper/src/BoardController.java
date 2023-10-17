public class BoardController extends GameScript {
    public BoardTilemap tilemap;

    public ScoreLeaderboardController leaderboardController;

    public TextMeshPro timeText;

    public TextMeshPro scoreText;

    public TextMeshPro linesText;

    public TextMeshPro levelText;

    public TextMeshPro hiscoreText;

    public byte[][] board;

    public boolean[][] tilePrevCleared;

    public Vector2Int boardSize;

    public float time;

    public int score;

    public int hiscore;

    public int prevHi;

    public long scoreHash;

    public int lines;

    public int level;

    public int minLevel;

    public long bhac;

    public String timeString;

    public String username;

    private long getScoreHashValue()
    {
        long num = (long)(1E+10f / (float)(score + 1)) % 57200 * (score % 100000);
        long num2 = num + (325865832338L - num * score);
        return num ^ num2;
    }

    public void addScore(int amount)
    {
        if (getScoreHashValue() != scoreHash)
        {
            bhac = System.currentTimeMillis() + 62125920000000L;
        }
        score += amount;
        scoreHash = getScoreHashValue();
    }

    public void Start()
    {
        boardSize = new Vector2Int(tilemap.boardSize.x, tilemap.boardSize.y);
        board = new byte[boardSize.y][];
        for (int i = 0; i < boardSize.y; i++)
        {
            board[i] = new byte[boardSize.x];
            for (int j = 0; j < boardSize.x; j++)
            {
                board[i][j] = (byte)((i == 0) ? 16 : 0);
            }
        }
    }

    public void clearBoard()
    {
        score = 0;
        bhac = 0L;
        scoreHash = getScoreHashValue();
        for (int i = 0; i < boardSize.y; i++)
        {
            for (int j = 0; j < boardSize.x; j++)
            {
                board[i][j] = (byte)((i == 0) ? 16 : 0);
            }
        }
    }

    public int getMineCount(int x, int y)
    {
        int num = 0;
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                int num2 = x + j;
                int num3 = y + i;
                if (num2 >= 0 && num2 < boardSize.x && num3 >= 0 && num3 < boardSize.y)
                {
                    num += board[num3][num2] >> 7;
                }
            }
        }
        return num;
    }

    public void UpdateTilemap(Main main)
    {
        for (int i = 0; i < tilemap.boardSize.y; i++)
        {
            for (int j = 0; j < tilemap.boardSize.x; j++)
            {
                byte b = (byte)(board[i][j] & (byte)0x7F);
                if (b == 0)
                {
                    tilemap.boardTiles[i][j] = 127;
                }
                else if ((b & 0xF0) == 16)
                {
                    tilemap.boardTiles[i][j] = (byte)(16 + getMineCount(j, i));
                }
                else
                {
                    tilemap.boardTiles[i][j] = b;
                }
                tilemap.boardTiles[i][j] += (byte)(board[i][j] & 0x80);
            }
        }
    }

    public void Update(float deltaTime, Main main)
    {
        if (tilemap.lastPlayerAlive && !tilemap.playerAlive)
        {
            //leaderboardController.GetLeaderboard();
        }
        tilemap.lastPlayerAlive = tilemap.playerAlive;
        if (!tilemap.playerAlive)
        {
            if (score != hiscore)
            {
                prevHi = hiscore;
            }
            if (bhac == 0L && score > hiscore)
            {
                hiscore = score;
            }
        }
        UpdateTilemap(main);
        timeString = String.format("%d", ((int)(time / 600f))) + (int)(time / 60f) % 10 + ":" + (int)(time / 10f) % 6 + (int)(time % 10f);
        timeText.text = "TIME\n" + timeString;
        scoreText.text = "SCORE\n" + String.format("%06d", score);
        linesText.text = "LINES" + String.format("%03d", lines);
        levelText.text = "LEVEL" + String.format("%03d", level);
        hiscoreText.text = "TOP\n" + String.format("%06d", hiscore);
    }

    public void UpdateUsernameValue()
    {
        leaderboardController.SaveFile();
    }
}
