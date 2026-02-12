package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu extends Scene {

    private Stage stage;
    private Skin skin;
    private SceneManager sceneManager;

    public Menu(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        
    }

    @Override
    public void onEnter(){
    stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // GIVE INPUT TO BUTTONS
        skin = SimpleSkin.getSkin();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create Buttons
        TextButton startBtn = new TextButton("START GAME", skin);
        TextButton exitBtn = new TextButton("EXIT", skin);

        // Add Listeners
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // SWITCH TO THE GAME SCENE!
                System.out.println("BUTTON WAS CLICKED!");
                sceneManager.setScene(new DropletScene(sceneManager));
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(startBtn).width(200).height(50).pad(10);
        table.row();
        table.add(exitBtn).width(200).height(50).pad(10);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void onExit() {
        stage.dispose();
        skin.dispose();
    }

}
