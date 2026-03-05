package inf1009.p63.flappyearth.engine.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class GameMaster extends ApplicationAdapter {

    private SceneManager sceneManager;
    private GameContextManager contextManager;

    @Override
    public void create() {
        // Initialize scene manager
        sceneManager = new SceneManager();
        
        // Initialize all managers and pre-create all scenes
        contextManager = new GameContextManager();
        contextManager.init(sceneManager);
        
        // Wire context manager to scene manager
        sceneManager.setContextManager(contextManager);
        
        // Start with menu scene using type-safe switching
        sceneManager.switchTo(SceneId.MENU);
    }

    @Override
    public void render() {
        // Update and render current scene each frame
        float delta = Gdx.graphics.getDeltaTime();
        sceneManager.update(delta);
        sceneManager.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        sceneManager.dispose();
        contextManager.dispose();
    }
}
