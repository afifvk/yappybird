package inf1009.p63.yappybird.lwjgl3;

import java.util.List;

public class MovementManager {
    public void update(List<Entity> entities, float delta) {
        for (Entity entity : entities) {
            if (entity instanceof iMovable) {
                ((iMovable) entity).movement(delta);
            }
        }
    }


    public void keepInBounds(TextureObject obj, float screenWidth){
        if (obj.rectangle.x < 0) {
            obj.rectangle.x = 0;
        }
        if(obj.rectangle.x > screenWidth - obj.rectangle.width){
            obj.rectangle.x = screenWidth - obj.rectangle.width;
        } 
            
    }

}
