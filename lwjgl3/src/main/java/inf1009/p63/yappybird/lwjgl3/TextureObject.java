package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TextureObject extends Entity implements iMovable {

    public Texture texture;
    public Rectangle rectangle;
    public Vector2 velocity;
    
    // Constructor matching the one you likely need
    public TextureObject(Texture tex, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.texture = tex;
        this.rectangle = new Rectangle(x, y, width, height);
        this.velocity = new Vector2(0,0);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    
    // Required by iMovable interface
    @Override
    public void movement(float delta) {
        this.rectangle.x += velocity.x * delta;
        this.rectangle.y += velocity.y * delta;
    }

    public void setVelocity(float x, float y){
        this.velocity.set(x, y);
    }


}
