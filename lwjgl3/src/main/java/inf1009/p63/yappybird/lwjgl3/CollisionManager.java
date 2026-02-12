package inf1009.p63.yappybird.lwjgl3;

public class CollisionManager {

    public boolean checkCollision(TextureObject object1, TextureObject object2) {
        // We use the LibGDX 'overlaps' method, but we wrap it here
        // so our Scene doesn't need to know the math details.
        return object1.rectangle.overlaps(object2.rectangle);
    }
    

}


