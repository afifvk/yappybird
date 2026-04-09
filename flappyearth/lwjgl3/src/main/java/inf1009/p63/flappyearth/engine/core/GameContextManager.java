package inf1009.p63.flappyearth.engine.core;

import inf1009.p63.flappyearth.engine.managers.AssetManager;
import inf1009.p63.flappyearth.engine.managers.CollisionManager;
import inf1009.p63.flappyearth.engine.managers.EventManager;
import inf1009.p63.flappyearth.engine.managers.InputOutputManager;
import inf1009.p63.flappyearth.engine.managers.MovementManager;
import inf1009.p63.flappyearth.engine.managers.RandomManager;
import inf1009.p63.flappyearth.engine.managers.SoundManager;
import inf1009.p63.flappyearth.engine.managers.TimeManager;
import inf1009.p63.flappyearth.game.scenes.GameOverScene;
import inf1009.p63.flappyearth.game.scenes.GameScene;
import inf1009.p63.flappyearth.game.scenes.MenuScene;

import java.util.HashMap;
import java.util.Map;

public class GameContextManager {

    private AssetManager assetManager;
    private InputOutputManager inputOutputManager;
    private MovementManager movementManager;
    private CollisionManager collisionManager;
    private SoundManager soundManager;
    private EventManager eventManager;
    private TimeManager timeManager;
    private RandomManager randomManager;

    // Pre-created scenes (DIP: only GameContextManager constructs scenes)
    private final Map<SceneId, Scene> scenes = new HashMap<>();

    public void init(SceneManager sceneManager) {
        //Initialize all core managers
        eventManager       = new EventManager();
        assetManager       = new AssetManager();
        inputOutputManager = new InputOutputManager();
        movementManager    = new MovementManager();
        collisionManager   = new CollisionManager();
        soundManager       = new SoundManager();
        timeManager        = new TimeManager();
        randomManager      = new RandomManager();

        // Load assets
        assetManager.load("assets/pipe.png", com.badlogic.gdx.graphics.Texture.class);
        // Load bird animation frames (6 frames)
        assetManager.load("assets/flappy00.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.load("assets/flappy01.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.load("assets/flappy02.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.load("assets/flappy03.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.load("assets/flappy04.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.load("assets/flappy05.png", com.badlogic.gdx.graphics.Texture.class);
        assetManager.finishLoading();

        // Pre-create all scenes (ONLY construction point for scenes)
        scenes.put(SceneId.MENU, new MenuScene(sceneManager, this));
        scenes.put(SceneId.GAME, new GameScene(sceneManager, this));
        scenes.put(SceneId.GAME_OVER, new GameOverScene(sceneManager, this));
    }

    public Scene getScene(SceneId id) {
        return scenes.get(id);
    }

    public void dispose() {
        // Clean up all resources
        if (assetManager       != null) assetManager.dispose();
        if (soundManager       != null) soundManager.dispose();
        if (eventManager       != null) eventManager.clearAll();
        // Dispose all scenes
        for (Scene scene : scenes.values()) {
            scene.disposeResources();
        }
    }

    public AssetManager getAssetManager()             { return assetManager; }
    public InputOutputManager getInputOutputManager() { return inputOutputManager; }
    public MovementManager getMovementManager()       { return movementManager; }
    public CollisionManager getCollisionManager()     { return collisionManager; }
    public SoundManager getSoundManager()             { return soundManager; }
    public EventManager getEventManager()             { return eventManager; }
    public TimeManager getTimeManager()               { return timeManager; }
    public RandomManager getRandomManager()           { return randomManager; }
}
