package me.mayhem.game.entity;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum EntityType {

    PLAYER(200, 1.05f, true, "players/PlayerSheet.png"),
    INFECTED(50,0.8f,  true, "enemies/Infected.png"),
    CORROSIVE(50,0.8f, true, "enemies/CorrosiveEnemy.png"),
    FEROCIOUS(100, 0.9f, true, "enemies/Ferocious.png"),
    PROJECTILE(0, 1f, false, "players/PlayerSheet.png"), //TODO: NEED TO ADD CORRECT IMAGE PATH FOR PROJECTILES
    KEY_CARD(0, 1f, false, "interactables/keycard/KeyCard.png"),
    DOOR(0, 1f, false, "interactables/doors/DoorClosed.png"),
    SPIKES(0, 1f, false, "obstacles/Spike.png")

    ;

    private final int maxHealth;
    private final float movementSpeed;
    private final String imagePath;
    private final Texture entityTexture;
    private final boolean healthBar;

    EntityType(int maxHealth, float movementSpeed, boolean healthBar, String imagePath) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.imagePath = imagePath;
        this.healthBar = healthBar;
        this.entityTexture = UtilImageLoader.loadTextureFromStream(getClass().getClassLoader().getResourceAsStream(this.imagePath));
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    public boolean getHasHealthBar() {
        return this.healthBar;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Texture getEntityTexture() {
        return this.entityTexture;
    }
}
