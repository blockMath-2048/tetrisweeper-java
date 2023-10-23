import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;

public class Main extends PApplet {
    public ArrayList<GameScript> scripts;

    int inputHandler;


    public void settings(){
        size(600,600);
    }
    public void setup(){
        scripts = new ArrayList<>();

        scripts.add(new BoardTilemap());
        scripts.get(0).Init(this);
        ((BoardTilemap)scripts.get(0)).boardSize = new Vector2Int(10, 20);
        ((BoardTilemap)scripts.get(0)).tileSize = new Vector2(32, 32);
        scripts.get(0).transform = new Vector3((float)width / 2, (float)height / 2, 0);
        scripts.add(new BoardController());
        ((BoardController)scripts.get(1)).tilemap = (BoardTilemap)scripts.get(0);
        scripts.add(new GameController());
        ((GameController)scripts.get(2)).board = (BoardController)scripts.get(1);
        scripts.add(new InputController());
        ((GameController)scripts.get(2)).input = (InputController)scripts.get(3);
        ((InputController)scripts.get(3)).board = (BoardController)scripts.get(1);
        inputHandler = 3; // REPLACE WITH INDEX IN <scripts> OF CLASS InputController
        scripts.add(new AudioController());
        ((GameController)scripts.get(2)).audioController = (AudioController)scripts.get(4);

        for (GameScript script : scripts) {
            script.Start();
        }
        prevFrame = Instant.now();
    }

    Instant prevFrame;
    public void draw(){
        float deltaTime = Time.from(prevFrame).getTime() / 1000.0f;
        Input.mousePosition = new Vector3(mouseX, mouseY, 0);
        prevFrame = Instant.now();
        for (GameScript script : scripts) {
            script.Update(deltaTime, this);
        }

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

