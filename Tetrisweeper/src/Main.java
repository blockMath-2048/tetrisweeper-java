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
        inputHandler = 0; // REPLACE WITH INDEX IN <scripts> OF CLASS InputController

        for (GameScript script : scripts) {
            script.Start();
        }
    }

    Instant prevFrame;
    public void draw(){
        float deltaTime = Time.from(prevFrame).getTime() / 1000.0f;
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

