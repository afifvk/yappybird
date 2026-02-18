package inf1009.p63.yappybird.lwjgl3;

import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<Entity>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }
    
    // THIS IS THE METHOD YOUR RENDERER NEEDS
    public ArrayList<Entity> getEntities() {
        return entityList;
    }

    // Optional: Removes a specific entity
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void dispose() {
        for (Entity e : entityList) {
            if (e instanceof TextureObject) {
                ((TextureObject) e).dispose();
            }
        }
        entityList.clear();
    }
}
