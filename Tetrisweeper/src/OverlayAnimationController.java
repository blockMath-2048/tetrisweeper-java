import java.util.Locale;

public class OverlayAnimationController extends GameScript {
    public float animationTimer = 1f;

    public int dir = 1;


    public TextMeshPro scoreText;

    public TextMeshPro timeText;

    public TextMeshPro hiscoreText;

    public BoardController board;

    public void Start() {
    }

    public void Update(float deltaTime, Main main) {
        scoreText.text = String.format(Locale.ENGLISH, "%7d", board.score);
        timeText.text = board.timeString;
        hiscoreText.text = String.format(Locale.ENGLISH, "%7d", board.hiscore);
        animationTimer += deltaTime * (float) dir;
        if (animationTimer >= 1f) {
            animationTimer = 1f;
        } else if (animationTimer <= 0f) {
            animationTimer = 0f;
        }
        this.transform = Vector3.add(new Vector3(0f, 0f, -1f), new Vector3(0f, -10f, 0f).mul(animationTimer * animationTimer));
    }

    public void StartAnimation(int direction) {
        dir = direction;
        animationTimer = ((dir == -1) ? 1f : 0f);
    }
}
