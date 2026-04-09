package inf1009.p63.flappyearth.game.entities;

import com.badlogic.gdx.math.Rectangle;
import inf1009.p63.flappyearth.engine.entities.Entity;
import inf1009.p63.flappyearth.engine.entities.RenderData;
import inf1009.p63.flappyearth.engine.interfaces.Collidable;
import inf1009.p63.flappyearth.engine.interfaces.Renderable;
import inf1009.p63.flappyearth.engine.interfaces.Updatable;

public abstract class GameEntity extends Entity implements Updatable, Renderable, Collidable {

    private static int idCounter = 0;

    protected final int    id;
    protected final String assetKey;
    protected float        colorR, colorG, colorB;

    public GameEntity(float x, float y, float width, float height, String assetKey) {
        this(x, y, width, height, assetKey, null);
    }

    public GameEntity(float x, float y, float width, float height, String assetKey, String tag) {
        super(x, y, width, height, tag);
        // Auto-increment unique ID for tracking
        this.id       = ++idCounter;
        this.assetKey = assetKey;
        // Default white color
        this.colorR   = 1f;
        this.colorG   = 1f;
        this.colorB   = 1f;
    }


    @Override
    public RenderData getRenderData() {
        boolean flipped = false;
        if (this instanceof Obstacle) {
            flipped = ((Obstacle) this).isFlipped();
        }
        return new RenderData(assetKey, bounds.x, bounds.y, bounds.width, bounds.height,
                              colorR, colorG, colorB, flipped);
    }


    @Override
    public Rectangle getBounds() {
        return bounds;
    }


    @Override
    public abstract void update(float delta);

    public int    getId()       { return id; }
    public String getAssetKey() { return assetKey; }
    public float  getColorR()   { return colorR; }
    public float  getColorG()   { return colorG; }
    public float  getColorB()   { return colorB; }

    protected void setColor(float r, float g, float b) {
        colorR = r; colorG = g; colorB = b;
    }
}
