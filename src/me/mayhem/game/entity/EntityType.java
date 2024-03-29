package me.mayhem.game.entity;

import me.mayhem.game.entity.entities.collect.key.KeyCard;
import me.mayhem.game.entity.entities.enemies.corrosive.CorrosiveEnemy;
import me.mayhem.game.entity.entities.enemies.ferocious.FerociousEnemy;
import me.mayhem.game.entity.entities.enemies.infected.InfectedEnemy;
import me.mayhem.game.entity.entities.friendly.door.Door;
import me.mayhem.game.entity.entities.obstacle.Spike;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilImageLoader;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.util.function.BiFunction;

public enum EntityType {

    PLAYER(200, 1.75f, 6f, true, "players/PlayerSheet.png", null),
    INFECTED(50,0.8f, 4f, true, "enemies/Infected.png", InfectedEnemy::new),
    CORROSIVE(50,0.8f, 4f, true, "enemies/Corrosive.png", CorrosiveEnemy::new),
    FEROCIOUS(100, 0.9f, 4f, true, "enemies/Ferrocious.png", FerociousEnemy::new),
    PROJECTILE(0, 1f, 4f, false, "projectiles/bluefireball.png", null), //TODO: NEED TO ADD CORRECT IMAGE PATH FOR PROJECTILES
    KEY_CARD(0, 1f, 4f, false, "interactables/keycard/KeyCard.png", KeyCard::new),
    VACCINE(0, 1f, 4f, false, "projectiles/LeftSyringe.png", null),
    DOOR(0, 0f, 0f, false, "interactables/doors/DoorClosed.png", Door::new),
    SPIKES(0, 0f, 0f, false, "obstacles/Spike.png", Spike::new),

    ;

    private final int maxHealth;
    private final float movementSpeed;
    private final float jumpStrength;
    private final Texture entityTexture;
    private final Sprite entitySprite;
    private final boolean healthBar;
    private final String imagePath;
    private final BiFunction<Vector, Level, Entity> spawnMethod;

    EntityType(int maxHealth, float movementSpeed, float jumpStrength, boolean healthBar, String imagePath, BiFunction<Vector, Level, Entity> spawnMethod) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.jumpStrength = jumpStrength;
        this.healthBar = healthBar;
        this.imagePath = imagePath;
        this.spawnMethod = spawnMethod;
        this.entityTexture = UtilImageLoader.loadTextureFromStream(imagePath);
        this.entitySprite = UtilSprite.loadFromPath(imagePath, 0.25f, 0.25f);
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    public float getJumpStrength() {
        return this.jumpStrength;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public boolean getHasHealthBar() {
        return this.healthBar;
    }

    public Texture getEntityTexture() {
        return this.entityTexture;
    }

    public Sprite getEntitySprite() {
        return this.entitySprite;
    }

    public BiFunction<Vector, Level, Entity> getSpawnMethod() {
        return this.spawnMethod;
    }
}
