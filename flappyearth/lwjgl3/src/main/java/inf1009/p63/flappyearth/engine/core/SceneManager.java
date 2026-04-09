package inf1009.p63.flappyearth.engine.core;

import java.util.Stack;

public class SceneManager {

    private Scene current;
    private final Stack<Scene> stack = new Stack<>();
    private GameContextManager contextManager; // Reference to get pre-created scenes

    public void setContextManager(GameContextManager contextManager) {
        this.contextManager = contextManager;
    }

    // Type-safe scene switching using pre-created scenes (DIP compliant)
    public void switchTo(SceneId sceneId) {
        if (contextManager == null) {
            throw new IllegalStateException("GameContextManager not set");
        }
        Scene next = contextManager.getScene(sceneId);
        if (next == null) {
            throw new IllegalArgumentException("Scene not found for ID: " + sceneId);
        }
        setScene(next);
    }

    public void setScene(Scene next) {
        // Exit current scene (but don't dispose - scenes are reused)
        if (current != null) {
            current.onExit();
        }
        // Clear stack without disposing (scenes are managed by GameContextManager)
        while (!stack.isEmpty()) {
            Scene s = stack.pop();
            s.onExit();
        }
        // Enter new scene
        current = next;
        if (current != null) {
            current.onEnter();
        }
    }

    // Push scene onto stack for later return
    public void pushScene(Scene next) {
        if (current != null) {
            stack.push(current);
            // Don't call onExit here - scene is being paused, not exited
        }
        current = next;
        if (current != null) {
            current.onEnter();
        }
    }

    // Return to previous scene
    public void popScene() {
        if (current != null) {
            current.onExit();
            // Don't dispose - scene is managed by GameContextManager
        }
        current = stack.isEmpty() ? null : stack.pop();
    }

    public void update(float delta) {
        if (current != null) {
            current.update(delta);
        }
    }

    public void render() {
        if (current != null) {
            current.render();
        }
    }

    public Scene getCurrent() {
        return current;
    }

    public void dispose() {
        if (current != null) {
            current.onExit();
            current.disposeResources();
        }
        while (!stack.isEmpty()) {
            Scene s = stack.pop();
            s.onExit();
            s.disposeResources();
        }
        current = null;
    }
}
