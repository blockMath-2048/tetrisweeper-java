public class AudioController extends GameScript {
    public AudioSource music;

    public AudioSource death;

    public AudioSource bell;

    public AudioSource menuScroll;

    public AudioSource menuSelect;

    public ArrayList<AudioSource> clearSFX;

    public int effectID;

    private float effectFrame;

    private float deathEffectFactor;

    public boolean musicActive = true;

    public void Start() {
        deathEffectFactor = 1f;
    }

    public void Update(float deltaTime, Main main) {
        if (musicActive) {
            music.mute = false;
        } else {
            music.mute = true;
        }
        effectFrame += deltaTime;
        if (effectID == 1) {
            deathEffectFactor -= deltaTime / 5f;
            if (deathEffectFactor < 0.01f) {
                deathEffectFactor = 1f;
                music.Stop();
                effectID = 0;
            }
            music.pitch = deathEffectFactor;
        }
    }

    public void playEffect(int effect) {
        effectID = effect;
        effectFrame = 0f;
        if (effect == 0) {
            music.Play();
        }
        if (effect == 1) {
            death.Play();
        }
        if (effect == 2) {
            bell.Play();
        }
        if (effect == 3) {
            menuScroll.Play();
        }
        if (effect == 4) {
            menuSelect.Play();
        }
        if (effect >= 16 && effect - 16 < clearSFX.size()) {
            clearSFX.get(effect - 16).Play();
        }
        if (effect - 16 >= clearSFX.Count) {
            clearSFX.get(clearSFX.size() - 1).Play();
        }
    }
}
