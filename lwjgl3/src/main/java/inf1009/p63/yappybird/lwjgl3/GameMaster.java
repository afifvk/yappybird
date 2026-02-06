package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//import inf1009.p63.yappybird.lwjgl3.scene.SceneManager;
//import inf1009.p63.yappybird.io.InputOutputManager;
//import inf1009.p63.yappybird.audio.SoundManager;
//import inf1009.p63.yappybird.scene.Scene;


public class GameMaster extends ApplicationAdapter {
	
    private SpriteBatch batch;
    private SceneManager sceneManager;
    private InputOutputManager inputManager;
    private SoundManager soundManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        inputManager = new InputOutputManager();
        soundManager = SoundManager.getInstance(null);
    }

    @Override
    public void render() {
        Scene current = sceneManager.getCurrent();
        if (current != null) {
            current.update(Gdx.graphics.getDeltaTime());
            current.render();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

	
}


