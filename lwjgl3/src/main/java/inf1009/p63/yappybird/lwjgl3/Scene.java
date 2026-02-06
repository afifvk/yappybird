package inf1009.p63.yappybird.lwjgl3;

public abstract class Scene {

    // Called when the scene becomes active
    public void onEnter() {
        // optional: override if needed
    }

    // Called when the scene is exited
    public void onExit() {
        // optional: override if needed
    }

    // Called every frame
    public abstract void update(float delta);

    // Called every frame
    public abstract void render();
}
