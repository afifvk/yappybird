package inf1009.p63.yappybird.lwjgl3;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;


public class EntityManager {

    private final List<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        entities.add(entity);
    }

    public void delete(Entity deleteEntity) {
        entities.remove(deleteEntity);
    }

    // UML name is getEntity(): List<Entity>
    public List<Entity> getEntity() {
        return Collections.unmodifiableList(entities);
    }
}