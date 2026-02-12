package inf1009.p63.yappybird.lwjgl3;


import java.util.Stack;

//import inf1009.p63.yappybird.ecs.EntityManager;

public class SceneManager {
	
    //private EntityManager entity;
    private Stack<Scene> stackScene;
    private Scene currentScene;
    //private boolean isPaused;

    public SceneManager() {
        //entity = new EntityManager();
        stackScene = new Stack<Scene>();
        currentScene = null;
        //isPaused = false;
    }

    public void pushScene(Scene scene) {
        if (scene == null)
            return;

        if (currentScene != null) {
            stackScene.push(currentScene);
            currentScene.onExit();
        }

        currentScene = scene;
        currentScene.onEnter();
    }

    public void popScene() {
        if (currentScene != null) {
            currentScene.onExit();
        }
        if (!stackScene.isEmpty()) {
            currentScene = stackScene.pop();
            currentScene.onEnter();
        } else {
            currentScene = null;
        }
    }

    public void setScene(Scene scene) {
        if (scene == null)
            return;

        if (currentScene != null) {
            currentScene.onExit();
        }

        stackScene.clear();
        currentScene = scene;
        currentScene.onEnter();
    }

    public Scene getCurrent() {
        return currentScene;
    }
    
    
}
