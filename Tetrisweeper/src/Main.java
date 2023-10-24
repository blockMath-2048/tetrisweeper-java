import processing.core.PApplet;
import processing.core.PFont;

import java.awt.event.KeyEvent;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends PApplet {
    public ArrayList<GameScript> scripts;
    PFont myFont;
    int inputHandler;


    public void settings(){
        size(800,900);
    }
    public void setup(){
        myFont = createFont("PressStart2P-Regular.ttf", 32);
        textFont(myFont);

        scripts = new ArrayList<>();

        scripts.add(new BoardTilemap());
        scripts.get(0).Init(this);
        ((BoardTilemap)scripts.get(0)).boardSize = new Vector2Int(10, 20);
        ((BoardTilemap)scripts.get(0)).tileSize = new Vector2(32, 32);
        scripts.get(0).transform = new Vector3((float)width / 2 - 160, (float)height / 2 - 360, 0);
        scripts.add(new BoardController());
        BoardController boardController = (BoardController)scripts.get(1);
        boardController.tilemap = (BoardTilemap)scripts.get(0);
        scripts.add(new GameController());
        GameController gameController = (GameController)scripts.get(2);
        gameController.board = boardController;
        gameController.clearStreakBonus = new ArrayList<>(Arrays.asList(0, 100, 300, 600, 1500, 2500, 5000, 10000));
        gameController.pieceRandMode = 7;
        scripts.add(new InputController());
        gameController.input = (InputController)scripts.get(3);
        ((InputController)scripts.get(3)).board = boardController;
        inputHandler = 3; // REPLACE WITH INDEX IN <scripts> OF CLASS InputController
        scripts.add(new AudioController());
        ((GameController)scripts.get(2)).audioController = (AudioController)scripts.get(4);
        scripts.add(new CursorSelectionBox());
        ((CursorSelectionBox)scripts.get(5)).input = (InputController)scripts.get(3);
        ((CursorSelectionBox)scripts.get(5)).tilemap = (BoardTilemap)scripts.get(0);

        scripts.add(boardController.timeText = new TextMeshPro(new Vector2(600, 700)));
        scripts.add(boardController.scoreText = new TextMeshPro(new Vector2(40, 500)));
        scripts.add(boardController.linesText = new TextMeshPro(new Vector2(40, 600)));
        scripts.add(boardController.levelText = new TextMeshPro(new Vector2(40, 700)));
        scripts.add(boardController.hiscoreText = new TextMeshPro(new Vector2(600, 500)));


        scripts.add(gameController.nextPieceText = new TextMeshPro(new Vector2(600, 200)));
        scripts.add(gameController.heldPieceText = new TextMeshPro(new Vector2(40, 200)));


        for (GameScript script : scripts) {
            script.Start();
        }
        prevFrame = Instant.now();
    }

    Instant prevFrame;
    public void draw(){
        background(0.0f, 0.0f, 64.0f);
        float deltaTime = Time.from(prevFrame).getTime() / 100000000000000.0f;
        //System.out.println(deltaTime);
        Input.mousePosition = new Vector3(mouseX, mouseY, 0);
        prevFrame = Instant.now();
        for (int i = scripts.size() - 1; i >= 0; i--) {
            scripts.get(i).Update(deltaTime, this);
        }
        for (int i = scripts.size() - 1; i >= 0; i--) {
            scripts.get(i).LateUpdate(deltaTime, this);
        }
        //fill(32.0f);
        //ellipse(Input.mousePosition.x, Input.mousePosition.y, 10, 10);
        //delay(10);
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) Input.keyboard['W'] = true;
            if (keyCode == DOWN) Input.keyboard['S'] = true;
            if (keyCode == LEFT) Input.keyboard['A'] = true;
            if (keyCode == RIGHT) Input.keyboard['D'] = true;
        } else {
            Input.keyboard[key] = true;
        }
    }

    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == UP) Input.keyboard['W'] = false;
            if (keyCode == DOWN) Input.keyboard['S'] = false;
            if (keyCode == LEFT) Input.keyboard['A'] = false;
            if (keyCode == RIGHT) Input.keyboard['D'] = false;
        } else {
            Input.keyboard[key] = false;
        }
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

