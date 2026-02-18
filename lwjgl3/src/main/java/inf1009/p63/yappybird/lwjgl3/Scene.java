package inf1009.p63.yappybird.lwjgl3;

public abstract class Scene {

    // The Data: Holds all your droplets and buckets
    protected EntityManager entity;
    
    // The Artist: Handles drawing everything
    protected RendererManager rendererManager;

    public Scene() {
        this.entity = new EntityManager();
        this.rendererManager = new RendererManager();
    }

    // Abstract methods: Child classes MUST define these
    public abstract void update(float delta); // Movement logic
    public abstract void render();            // Drawing logic
    public abstract void onEnter();           // Setup (load textures)
    public abstract void onExit();            // Cleanup (dispose textures)

    protected void disposeResources() {
        if (rendererManager != null) {
            rendererManager.dispose();
        }
        if (entity != null) {
            entity.dispose();
        }
    }

    // Helper to let the Renderer see the entities
    public EntityManager getEntity() {
        return entity;
    }

}
