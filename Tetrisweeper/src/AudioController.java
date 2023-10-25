import java.util.ArrayList;
import processing.sound.*;

public class AudioController extends GameScript {
    public SoundFile music;

    public SoundFile death;

    public SoundFile bell;

    public SoundFile menuScroll;

    public SoundFile menuSelect;

    public ArrayList<SoundFile> clearSFX;

    public int effectID;

    private float effectFrame;

    private float deathEffectFactor;

    public boolean musicActive = true;

    public void Init(Main main) {
        music = new SoundFile(main, "Sounds/music.mp3");
        death = new SoundFile(main, "Sounds/lose.mp3");
        bell = new SoundFile(main, "Sounds/bell-flag.mp3");
        menuScroll = new SoundFile(main, "Sounds/menuScroll.mp3");
        menuSelect = new SoundFile(main, "Sounds/menuSelect.mp3");
        clearSFX = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            clearSFX.add(new SoundFile(main, String.format("Sounds/clear%X.mp3", i)));
            clearSFX.get(i).amp(0.5f);
        }
    }

    public void Start() {
        deathEffectFactor = 1f;
    }

    public void Update(float deltaTime, Main main) {
        effectFrame += deltaTime;
        if (effectID == 1) {
            deathEffectFactor -= deltaTime / 5f;
            if (deathEffectFactor < 0.01f) {
                deathEffectFactor = 1f;
                music.stop();
                effectID = 0;
            }
            music.rate(deathEffectFactor);
        }
    }

    public void playEffect(int effect) {
        effectID = effect;
        effectFrame = 0f;
        if (effect == 0) {
            music.play();
        }
        if (effect == 1) {
            death.play();
        }
        if (effect == 2) {
            bell.play();
        }
        if (effect == 3) {
            menuScroll.play();
        }
        if (effect == 4) {
            menuSelect.play();
        }
        if (effect >= 16 && effect - 16 < clearSFX.size()) {
            clearSFX.get(effect - 16).play();
        }
        if (effect - 16 >= clearSFX.size()) {
            clearSFX.get(clearSFX.size() - 1).play();
        }
    }
}
