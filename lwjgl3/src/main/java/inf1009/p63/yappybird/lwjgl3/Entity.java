package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    protected Rectangle bounds;
    protected float x, y;
    
    public Entity(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x,y,width,height);
    }

    //Encapsulation
    public float getX() { return bounds.x; }
    public float getY() { return bounds.y; }
     
    public Rectangle getBounds() {
        return bounds;
    }


}
