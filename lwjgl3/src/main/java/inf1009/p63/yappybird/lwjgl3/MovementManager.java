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
        if (obj.getRectangle().x < 0) {
            obj.getRectangle().x = 0;
        }
        if(obj.getRectangle().x > screenWidth - obj.getRectangle().width){
            obj.getRectangle().x = screenWidth - obj.getRectangle().width;
        } 
            
    }

}
