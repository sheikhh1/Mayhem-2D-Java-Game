package me.mayhem.game.entity;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum EntityType {

    PLAYER(200, 1.05f, "players/PlayerSheet.png"),
    INFECTED(50,0.8f,  "enemies/Infected.png"),
    CORROSIVE(50,0.8f, "enemies/CorrosiveEnemy.png"),
    FEROCIOUS(100, 0.9f, "enemies/Ferocious.png"),
    PROJECTILE(0, 1f, "players/PlayerSheet.png"), //TODO: NEED TO ADD CORRECT IMAGE PATH FOR PROJECTILES
    KEYCARD(0, 1f, "interactables/keycard/KeyCard.png")

    ;

    private final int maxHealth;
    private final float movementSpeed;
    private final String imagePath;
    private final Texture entityTexture;

    EntityType(int maxHealth, float movementSpeed, String imagePath) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.imagePath = imagePath;
        this.entityTexture = UtilImageLoader.loadTextureFromStream(getClass().getClassLoader().getResourceAsStream(this.imagePath));
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Texture getEntityTexture() {
        return this.entityTexture;
    }
}
