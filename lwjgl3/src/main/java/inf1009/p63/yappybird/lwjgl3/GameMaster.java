package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GameMaster extends ApplicationAdapter {
	
    //private SpriteBatch batch;
    private SceneManager sceneManager;
    //private InputOutputManager inputManager;
    //private SoundManager soundManager;
    //private Scene currentScene;

    @Override
    public void create() {
        //batch = new SpriteBatch();
        sceneManager = new SceneManager();
        //inputManager = new InputOutputManager();
        //soundManager = SoundManager.getInstance(null);
        sceneManager.pushScene(new Menu(sceneManager));
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Scene current = sceneManager.getCurrent();
        if (current != null) {
            current.update(Gdx.graphics.getDeltaTime());
            current.render();
        }
    }

    @Override
    public void dispose() {
        //batch.dispose();
    }

	
}


