package me.mayhem.game.entity;

public enum EntityType {

    PLAYER(200, 1.05f),

    ;

    private final int maxHealth;
    private final float movementSpeed;

    EntityType(int maxHealth, float movementSpeed) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public float getMovementSpeed() {
        return this.movementSpeed;
    }
}
