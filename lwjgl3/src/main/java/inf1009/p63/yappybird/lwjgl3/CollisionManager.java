package inf1009.p63.yappybird.lwjgl3;

public class CollisionManager {

    public boolean checkCollision(TextureObject object1, TextureObject object2) {
        return object1.rectangle.overlaps(object2.rectangle);
    }
    // Droplet + Bucket  -> Accuracy (%)

    /**
     * Accuracy is based on how close the droplet's center is to the bucket's center.
     * - Perfectly centered = 100%
     * - Near bucket edge   = close to 0%
     */
    
    public float calculateCatchAccuracyPercent(TextureObject droplet, TextureObject bucket) {
        float dropletCenterX = droplet.rectangle.x + (droplet.rectangle.width / 2f);
        float bucketCenterX  = bucket.rectangle.x  + (bucket.rectangle.width  / 2f);

        float distance = Math.abs(dropletCenterX - bucketCenterX);

        // If droplet center is at bucket edge (half width away), accuracy should be ~0.
        float maxDistance = bucket.rectangle.width / 2f;
        if (maxDistance <= 0f) return 0f;

        float accuracy = 100f - ((distance / maxDistance) * 100f);

        // Clamp to 0..100
        if (accuracy < 0f) accuracy = 0f;
        if (accuracy > 100f) accuracy = 100f;

        return accuracy;
    }

    public String buildAccuracyPopupText(float accuracyPercent) {
        return "Accuracy: " + Math.round(accuracyPercent) + "%";
    }
    // Coin + Bucket -> Slowdown (%)
    
    /**
     * Convert timeScale to slowdown percentage.
     * Example:
     * - timeScale 1.0 => 0% slowdown
     * - timeScale 0.5 => 50% slowdown
     */
    
    public float timeScaleToSlowdownPercent(float timeScale) {
        float slowdown = (1f - timeScale) * 100f;

        // Clamp to 0..100
        if (slowdown < 0f) slowdown = 0f;
        if (slowdown > 100f) slowdown = 100f;

        return slowdown;
    }

    public String buildSlowdownPopupText(float slowdownPercent) {
        return "Droplets slowed: " + Math.round(slowdownPercent) + "%";
    }
}
