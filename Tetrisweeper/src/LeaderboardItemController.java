import java.util.Locale;

public class LeaderboardItemController extends GameScript {
    public int rank;

    public String displayName;

    public int score;

    public TextMeshPro rankText;

    public TextMeshPro nameText;

    public TextMeshPro scoreText;

    public void Start() {
    }

    public void Update(float deltaTime, Main main) {
        rankText.text = "#" + rank;
        nameText.text = displayName.toUpperCase(Locale.ENGLISH);
        scoreText.text = String.format(Locale.ENGLISH, "%7d", score);
    }
}
