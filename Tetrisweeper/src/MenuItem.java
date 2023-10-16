import java.util.ArrayList;

public class MenuItem extends GameScript {
    public InputController input;

    public AudioController audioController;

    public MenuController menu;

    public TextMeshPro display;

    public ArrayList<String> options;

    public int selection;

    public boolean isSelected;

    public void Start() {
    }

    public void Update(float deltaTime, Main main) {
        if (!menu.game.board.tilemap.paused) {
            return;
        }
        if (isSelected) {
            if ((Object) (object) display == null) {
                if (input.rotLeft > 0 || input.rotRight > 0) {
                    input.rotLeft = (input.rotRight = 0);
                    menu.ExecuteSelection();
                }
                return;
            }
            display.text = "< " + options.get(selection) + " >";
            if (input.left > 0) {
                input.left--;
                if (--selection < 0) {
                    selection += options.size();
                }
                audioController.playEffect(3);
            }
            if (input.right > 0) {
                input.right--;
                if (++selection >= options.size()) {
                    selection -= options.size();
                }
                audioController.playEffect(3);
            }
        } else if ((Object) (object) display != null) {
            display.text = "  " + options.get(selection) + "  ";
        }
    }
}
