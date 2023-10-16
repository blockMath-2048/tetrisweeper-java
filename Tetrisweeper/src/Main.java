import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public ArrayList<GameScript> scripts;

    public void settings(){
        size(600,600);
    }
    public void setup(){

        for (GameScript script : scripts) {
            script.Start();
        }
    }
    public void draw(){
        for (GameScript script : scripts) {
            script.Update(deltaTime, this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

