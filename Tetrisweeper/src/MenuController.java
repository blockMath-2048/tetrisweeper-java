import java.util.ArrayList;

import static javafx.application.Platform.exit;

public class MenuController extends GameScript {
    public GameController game;

    public AudioController audioController;

    public GameScript cursor;

    public GameScript pauseMenu;

    public InputController input;

    public ArrayList<MenuItem> menuItems;

    public int selection;

    public boolean selectionActive;

    public boolean pauseMenuActive;

    public float animationFactor;

    public float animationPower;

    public float animationSpeed;

    public void Start() {
    }

    public Vector3 lerp(Vector3 a, Vector3 b, float f) {
        return Vector3.add(a.mul(f), b.mul(1f - f));
    }

    public void ExecuteSelection() {
        if (selection == 0) {
            selectionActive = false;
        } else {
            exit();
        }
    }

    public void Update(float deltaTime, Main main) {
        if (!this.game.board.tilemap.paused) {
            return;
        }
        cursor.transform = Vector3.add(pauseMenu.transform, Vector3.add(new Vector3(-2.5f, 1.75f, 0f), new Vector3(0f, -0.75f * selection, 0f)));
        if (!selectionActive) {
            if (!pauseMenuActive) {
                selection = 0;
                animationFactor += deltaTime * animationSpeed;
                if (animationFactor >= 1f) {
                    animationFactor = 1f;
                    pauseMenuActive = true;
                    selectionActive = true;
                    input.clear();
                }
            } else {
                animationFactor -= deltaTime * animationSpeed;
                if (animationFactor <= 0f) {
                    animationFactor = 0f;
                    pauseMenuActive = false;
                    selectionActive = false;
                    this.game.board.tilemap.paused = false;
                    input.clear();
                }
            }
            pauseMenu.transform = lerp(new Vector3(0f, 0f, -2f), new Vector3(0f, 10f, -2f), (float)Math.pow(animationFactor, animationPower));
            return;
        }
        if (input.pause > 0) {
            input.pause--;
            selectionActive = false;
        }
        if (input.down > 0) {
            input.down--;
            if (++selection >= menuItems.size()) {
                selection -= menuItems.size();
            }
            audioController.playEffect(4);
        }
        if (input.hold > 0) {
            input.hold--;
            if (--selection < 0) {
                selection += menuItems.size();
            }
            audioController.playEffect(4);
        }
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).isSelected = i == selection;
        }
    }
}
