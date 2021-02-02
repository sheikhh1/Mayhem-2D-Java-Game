package me.mayhem.game.entity;

public enum EntityType {

    PLAYER(200, 1.05f),
    INFECTED(50,0.8f),
    CORROSIVE(50,0.8f),
    FEROCIOUS(100, 0.9f),
    PROJECTILE(0, 1f),
    KEYCARD(0, 1f)

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
