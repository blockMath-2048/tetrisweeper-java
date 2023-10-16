public class OverlayAnimationController extends GameScript {
    public float animationTimer = 1f;

    public int dir = 1;

    public GameScript nameText;

    public TextMeshPro scoreText;

    public TextMeshPro timeText;

    public TextMeshPro hiscoreText;

    public BoardController board;

    public void Start() {
    }

    public void Update(float deltaTime, Main main) {
        scoreText.text = board.score.ToString("0000000");
        timeText.text = board.timeString;
        hiscoreText.text = board.hiscore.ToString("0000000");
        nameText.SetActive(dir == -1 && animationTimer == 0f);
        animationTimer += deltaTime * (float) dir;
        if (animationTimer >= 1f) {
            animationTimer = 1f;
        } else if (animationTimer <= 0f) {
            animationTimer = 0f;
        }
        this.transform = new Vector3(0f, 0f, -1f) + animationTimer * animationTimer * new Vector3(0f, -10f, 0f);
    }

    public void StartAnimation(int direction) {
        dir = direction;
        animationTimer = ((dir == -1) ? 1f : 0f);
    }
}
