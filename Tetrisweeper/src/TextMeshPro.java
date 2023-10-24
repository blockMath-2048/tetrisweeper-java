public class TextMeshPro extends GameScript {
    public String text;

    TextMeshPro(Vector2 position) {
        transform = new Vector3(position.x, position.y, 0.0f);
        text = "";
    }

    public void Start() {

    }

    public void Update(float deltaTime, Main main) {
        main.fill(255.0f);
        main.text(text, transform.x, transform.y);
    }
}
