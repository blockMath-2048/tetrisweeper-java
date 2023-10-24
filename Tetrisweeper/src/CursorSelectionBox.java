public class CursorSelectionBox extends GameScript {
    public InputController input;
    public BoardTilemap tilemap;

    public void Start() {

    }

    public void LateUpdate(float deltaTime, Main main) {
        main.fill(0.0f, 0.0f);
        main.stroke(255.0f, 255.0f, 0.0f);
        main.strokeWeight(5);
        main.rect(input.selectionPos.x * tilemap.tileSize.x + tilemap.transform.x, (tilemap.boardSize.y - input.selectionPos.y) * tilemap.tileSize.y + tilemap.transform.y, tilemap.tileSize.x, tilemap.tileSize.y);
    }
}
