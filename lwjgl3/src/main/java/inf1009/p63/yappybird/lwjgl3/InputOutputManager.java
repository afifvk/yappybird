package inf1009.p63.yappybird.lwjgl3;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class InputOutputManager {
// Stores currently pressed keys
    private Set<Integer> keysPressed;
    
    // Mouse button states
    private int click_x;
    private int click_y;
    private boolean mousePressed;
    
    // Mouse position
    private Vector2 mousePosition;
    
    /**
     * Constructor initializes the input manager.
     */
    public InputOutputManager() {
        this.keysPressed = new HashSet<>();
        this.click_x = 0;
        this.click_y = 0;
        this.mousePressed = false;
        this.mousePosition = new Vector2();
    }
    
    /**
     * Updates the input manager state.
     * Should be called once per frame.
     */
    public void update(float delta) {
        
        // Update mouse position
        mousePosition.x = Gdx.input.getX();
        mousePosition.y = Gdx.input.getY();
        
        // Update mouse pressed state
        mousePressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        
        // If mouse is pressed, update click coordinates
        if (mousePressed) {
            click_x = Gdx.input.getX();
            click_y = Gdx.input.getY();
        }
        
        // Update key states
        updateKeyStates();
    }
    
    /**
     * Internal method to update which keys are currently pressed.
     */
    private void updateKeyStates() {
        // Clear and rebuild the pressed keys set
        // This is a simplified approach - a more robust implementation
        // would use InputProcessor for event-driven input
        keysPressed.clear();
        
        // Check common keys
        int[] commonKeys = {
            Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D,
            Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT,
            Input.Keys.SPACE, Input.Keys.SHIFT_LEFT, Input.Keys.CONTROL_LEFT,
            Input.Keys.ESCAPE, Input.Keys.ENTER, Input.Keys.NUM_1, 
            Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5
        };
        
        for (int key : commonKeys) {
            if (Gdx.input.isKeyPressed(key)) {
                keysPressed.add(key);
            }
        }
    }
    
    /**
     * Checks if a specific key is currently pressed.
     * @param key The key code to check (use Input.Keys constants)
     * @return boolean True if the key is pressed
     */
    public boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }
    
    /**
     * Checks if a specific key was just pressed this frame.
     * @param key The key code to check
     * @return boolean True if the key was just pressed
     */
    public boolean isKeyJustPressed(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }
    
    /**
     * Gets the current mouse position in screen coordinates.
     * @return Vector2 The mouse position
     */
    public Vector2 getMousePosition() {
        return mousePosition.cpy(); // Return a copy to prevent external modification
    }
    
    /**
     * Gets the X coordinate of the last mouse click.
     * @return int The X coordinate
     */
    public int getClickX() {
        return click_x;
    }
    
    /**
     * Gets the Y coordinate of the last mouse click.
     * @return int The Y coordinate
     */
    public int getClickY() {
        return click_y;
    }
    
    /**
     * Checks if the left mouse button is currently pressed.
     * @return boolean True if the mouse is pressed
     */
    public boolean isMousePressed() {
        return mousePressed;
    }
    
    /**
     * Checks if the left mouse button was just clicked this frame.
     * @return boolean True if the mouse was just clicked
     */
    public boolean isMouseJustClicked() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }
    
    /**
     * Checks if the right mouse button is pressed.
     * @return boolean True if the right mouse button is pressed
     */
    public boolean isRightMousePressed() {
        return Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
    }
    
    /**
     * Gets the set of all currently pressed keys.
     * @return Set<Integer> The set of pressed key codes
     */
    public Set<Integer> getKeysPressed() {
        return new HashSet<>(keysPressed); // Return a copy
    }
    
    /**
     * Gets the current mouse X coordinate.
     * @return int The mouse X position
     */
    public int getMouseX() {
        return (int) mousePosition.x;
    }
    
    /**
     * Gets the current mouse Y coordinate.
     * @return int The mouse Y position
     */
    public int getMouseY() {
        return (int) mousePosition.y;
    }
    
    /**
     * Checks if any key is currently pressed.
     * @return boolean True if any key is pressed
     */
    public boolean isAnyKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.ANY_KEY);
    }
    
    /**
     * Sets the cursor visibility.
     * @param visible True to show cursor, false to hide
     */
    public void setCursorVisible(boolean visible) {
        Gdx.input.setCursorCatched(!visible);
    }
    
    /**
     * Resets the input state.
     * Clears all tracked input.
     */
    public void reset() {
        keysPressed.clear();
        click_x = 0;
        click_y = 0;
        mousePressed = false;
        mousePosition.set(0, 0);
    }
}

