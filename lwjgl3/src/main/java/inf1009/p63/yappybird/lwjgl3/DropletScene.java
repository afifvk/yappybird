package inf1009.p63.yappybird.lwjgl3;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DropletScene extends Scene {

    private Texture bucketImage;
    private Texture dropImage;
    private Texture backgroundTexture; // For the street scene
    private Texture rainOverlayTexture;
    private float bucketVelY = 0f;
    private static final float GRAVITY = -900f;      // down
    private static final float JUMP_SPEED = 450f;    // up
    private static final float GROUND_Y = 20f; 

    //private Sound dropSound;
    //private Sound rainMusic;

    private InputOutputManager inputManager;
    private MovementManager movementManager;
    private CollisionManager collisionManager;
    private SceneManager sceneManager;

    private Stage stage;
    private Label scoreLabel;
    private Skin skin;
    
    

    
    int dropsGathered = 0;
    int dropsMissed = 0;
    private float rainOffset = 0;
    
    // We need to keep track of the bucket specifically to move it
    private TextureObject bucket;
    private long lastDropTime;

    public DropletScene(SceneManager sm) {
        this.sceneManager = sm;
    }

    @Override
    public void onEnter() {

        this.inputManager = new InputOutputManager();
        this.movementManager = new MovementManager();
        // 1. Load the images
        // NOTE: Make sure these names match your assets exactly!
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        dropImage = new Texture(Gdx.files.internal("droplet.png")); // or water.png
        backgroundTexture = new Texture(Gdx.files.internal("background.jpg")); // Make sure this exists!
        rainOverlayTexture = new Texture(Gdx.files.internal("rain_overlay.jpg")); // Use JPG or PNG

        SoundManager.getInstance(null).loadSound("drop", "droplet.mp3");
    
        // 2. Load background music (optional)
        SoundManager.getInstance(null).loadMusic("rain", "rain.mp3");
        SoundManager.getInstance(null).playMusic("rain", true); // true = loop

        // 3. Create the Bucket
        // (Texture, x, y, width, height)
        bucket = new TextureObject(bucketImage, 640 / 2 - 64 / 2, 20, 64, 64);
        collisionManager = new CollisionManager();
        
        // 4. Add bucket to the Entity Manager so it gets drawn
        this.entity.addEntity(bucket);
        
        // 5. Spawn the first raindrop
        spawnRaindrop();

        // 6. Setup UI Stage for score display
        stage = new Stage(new ScreenViewport());
        
        // 2. Get your Skin (same one used in Menu)
        this.skin = SimpleSkin.getSkin();
        
        // 3. Create the Score Label
        // "Score: 0" is the initial text
        scoreLabel = new Label("Score: 0 | Missed: 0/10", skin);
        
        // 4. Position it (Top Left)
        scoreLabel.setPosition(20, 480 - 40); // 20px from left, 40px from top
        scoreLabel.setFontScale(1.5f); // Make it a bit bigger
        
        // 5. Add to Stage
        stage.addActor(scoreLabel);
    }

    @Override
    public void update(float delta) {
        // INPUT LOGIC 
        inputManager.update(delta);

        // Reset only X input each frame
        bucket.velocity.x = 0;

        if(inputManager.isKeyPressed(Input.Keys.LEFT))  bucket.velocity.x -= 200;
        if(inputManager.isKeyPressed(Input.Keys.RIGHT)) bucket.velocity.x += 200;

        //  JUMP TRIGGER only if on the ground.
        boolean onGround = bucket.rectangle.y <= GROUND_Y + 0.5f;
        if (onGround && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bucketVelY = JUMP_SPEED;
        }

        // GRAVITY 
        bucketVelY += GRAVITY * delta;
        bucket.velocity.y = bucketVelY;

        // Keep bucket in bounds horizontally
        movementManager.keepInBounds(bucket, 640);

        // Apply movement to all entities
        movementManager.update(this.entity.getEntities(), delta);

        // GROUND CLAMP (after movement)
        if (bucket.rectangle.y < GROUND_Y) {
            bucket.rectangle.y = GROUND_Y;
            bucketVelY = 0f;
            bucket.velocity.y = 0f;
        }

        //Rain animation effect
        rainOffset -= 500 * delta;
        if(rainOffset < -480) rainOffset = 0;

        // SPAWN LOGIC 
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        handleCollisions();
        stage.act(delta);
    }

    private void handleCollisions(){
        Iterator<Entity> iter = this.entity.getEntities().iterator();
        while(iter.hasNext()) {
            Entity e = iter.next();
            
            // We only care about moving droplets, not the bucket
            if(e instanceof TextureObject && e != bucket) {
                TextureObject drop = (TextureObject) e;
                
                // Check 1: Did we catch it?
                if(collisionManager.checkCollision(drop, bucket)) {
                    dropsGathered++; // Increase score
                    scoreLabel.setText("Score: " + dropsGathered); //Print to UI 
                    SoundManager.getInstance(null).playSound("drop");
                    iter.remove(); // Remove it once!
                }
        
                else if(drop.rectangle.y + 64 < 0) {
                    dropsMissed++;
                    scoreLabel.setText("Score: " + dropsGathered + " | Missed: " + dropsMissed + "/10");
                    System.out.println("Missed: " + dropsMissed);

                    // check if drops missed reached 10
                    if(dropsMissed >=  10) {
                        sceneManager.setScene(new GameOver(sceneManager, dropsGathered));
                        return;
                    }
                    iter.remove();
                }
               
            }
        }
    }

    @Override
    public void render() {

        // 1. Draw Background and Animated Rain
        this.rendererManager.getBatch().begin();
        // Draw static street scene
        this.rendererManager.getBatch().draw(backgroundTexture, 0, 0, 640, 480);
        // Draw looping rain overlay
        this.rendererManager.getBatch().draw(rainOverlayTexture, 0, rainOffset, 640, 480);
        this.rendererManager.getBatch().draw(rainOverlayTexture, 0, rainOffset + 480, 640, 480);
        this.rendererManager.getBatch().end();

        // Ask the Manager to draw everything in our list
        this.rendererManager.render(this);
        stage.draw();
    }

    @Override
    public void onExit() {
        // Clean up memory
        if(bucketImage != null) bucketImage.dispose();
        if(dropImage != null) dropImage.dispose();
        if(backgroundTexture != null) backgroundTexture.dispose();
        if(rainOverlayTexture != null) rainOverlayTexture.dispose();
        if(stage != null) stage.dispose();
        if(skin != null) skin.dispose();
    }
    
    // Helper function to create a drop
    private void spawnRaindrop() {
        TextureObject drop = new TextureObject(dropImage, MathUtils.random(0, 800-64), 480, 64, 64);
        drop.setVelocity(0,-200);
        this.entity.addEntity(drop);
        lastDropTime = TimeUtils.nanoTime();
    }
}
