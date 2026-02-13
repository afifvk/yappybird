package inf1009.p63.yappybird.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class RendererManager {

     private SpriteBatch batch;
    private ShapeRenderer shape;

    public SpriteBatch getBatch(){
        return batch;
    }

    public RendererManager() {
        this.batch = new SpriteBatch();
        this.shape = new ShapeRenderer();
    }

    // Requirement: render(scene: scene): void
    public void render(Scene scene) {
        // We get the list from the scene and pass it to the list-renderer
        render(scene.getEntity().getEntities());
    }

    // Requirement: render(entity: List<Entity>): void
    public void render(List<Entity> entities) {
        batch.begin();
        for (Entity e : entities) {
            if (e instanceof TextureObject) {
                TextureObject obj = (TextureObject) e;
                // We extract the 'sprite' (texture) and 'transform' (rectangle)
                renderSprite(obj.texture, obj.rectangle);
            }
        }
        batch.end();
    }

    // Requirement: rendersprite(sprite, transform): void
    // Note: In 2D, a Rectangle acts as the 'transform' (Position & Size)
    public void renderSprite(Texture sprite, Rectangle transform) {
        batch.draw(sprite, transform.x, transform.y, transform.width, transform.height);
    }
    
    public void dispose() {
        batch.dispose();
        shape.dispose();
    }
}
