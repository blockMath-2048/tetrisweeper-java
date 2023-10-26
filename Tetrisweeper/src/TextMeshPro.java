import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;

public class TextMeshPro extends GameScript {
    public String text;
    public boolean rightAlign;

    TextMeshPro(Vector2 position) {
        transform = new Vector3(position.x, position.y, 0.0f);
        text = "";
        rightAlign = false;
    }

    public void Start() {

    }

    public void Update(float deltaTime, Main main) {
        main.fill(255.0f);
        if (rightAlign) main.textAlign(RIGHT); else main.textAlign(LEFT);
        main.text(text, transform.x, transform.y);
    }
}
