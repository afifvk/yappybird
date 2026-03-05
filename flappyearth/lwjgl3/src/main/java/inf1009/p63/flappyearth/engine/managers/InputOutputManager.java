package inf1009.p63.flappyearth.engine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputOutputManager {

    public boolean isKeyJustPressed(int keycode) {
        return Gdx.input.isKeyJustPressed(keycode);
    }

    public boolean isKeyPressed(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }

    public boolean isTouchJustPressed() {
        return Gdx.input.justTouched();
    }

    public boolean isTouched() {
        return Gdx.input.isTouched();
    }

    public boolean isFlapJustPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched();
    }
}
