public class ScoreLeaderboardController extends GameScript {
    public GameController game;

    public GameMessageController gameMsg;

    public OverlayAnimationController overlay;

    public List<LeaderboardItemController> leaderboardItems;

    public LeaderboardItemController selectedItem;

    private boolean lastAlive = true;

    private final String leaderboardPublicKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    private void Awake() {
        LoadFile();
    }

    public void Start() {
        GetLeaderboard();
        gameMsg.bhac = "";
    }

    public void LateUpdate() {
        if (lastAlive && !game.board.tilemap.playerAlive) {
            overlay.StartAnimation(-1);
            gameMsg.newHigh = game.board.score > game.board.prevHi;
            SaveFile();
        }
        if (!lastAlive && game.board.tilemap.playerAlive) {
            overlay.StartAnimation(1);
        }
        lastAlive = game.board.tilemap.playerAlive;
    }

    public int max(int a, int b) {
        if (a <= b) {
            return b;
        }
        return a;
    }

    public void SaveFile() {
        string path = Application.persistentDataPath + "/persistent.dat";
        FileStream fileStream = ((!File.Exists(path)) ? File.Create(path) : File.OpenWrite(path));
        GameData graph = new GameData(max(game.board.hiscore, game.board.score), game.board.username);
        new BinaryFormatter().Serialize(fileStream, graph);
        fileStream.Close();
    }

    public void LoadFile() {
        string path = Application.persistentDataPath + "/persistent.dat";
        if (File.Exists(path)) {
            FileStream fileStream = File.OpenRead(path);
            GameData gameData = (GameData) new BinaryFormatter().Deserialize(fileStream);
            fileStream.Close();
            game.board.hiscore = gameData.hiscore;
            game.board.username = gameData.username;
        } else {
            game.board.hiscore = 0;
        }
    }

    public void onFetchLeaderboard(Entry[] msg) {
        for (int i = 0; i < leaderboardItems.Count; i++) {
            leaderboardItems[i].rank = i + 1;
            if (i >= msg.Length) {
                leaderboardItems[i].displayName = "-";
                leaderboardItems[i].score = 0;
            } else {
                leaderboardItems[i].displayName = msg[i].Username;
                leaderboardItems[i].score = msg[i].Score;
            }
        }
        for (int j = 0; j < msg.Length; j++) {
            if (msg[j].Username == game.board.username) {
                selectedItem.rank = j + 1;
                selectedItem.displayName = msg[j].Username;
                selectedItem.score = msg[j].Score;
            }
        }
    }

    public void GetLeaderboard() {
        LeaderboardCreator.GetLeaderboard(leaderboardPublicKey, delegate(Entry[]msg)
        {
            onFetchLeaderboard(msg);
        });
    }

    public void UpdateLeaderboard() {
        gameMsg.bhac = "";
        if (game.board.score == 0) {
            return;
        }
        game.board.addScore(0);
        if (game.board.bhac == 0L) {
            LeaderboardCreator.UploadNewEntry(leaderboardPublicKey, game.board.username.ToLower(), game.board.score, delegate
            {
                GetLeaderboard();
            },delegate(string msg)
            {
                gameMsg.bhac = msg;
            });
        } else {
            gameMsg.bhac = "COULD NOT VALIDATE AUTHENTICATION STATE.";
        }
    }
}