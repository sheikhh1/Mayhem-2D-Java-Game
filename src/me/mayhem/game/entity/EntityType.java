package me.mayhem.game.entity;

import java.security.cert.TrustAnchor;

public enum EntityType {

    PLAYER(200, 1.05f, Boolean.TRUE),

    INFECTED(50,0.8f, Boolean.TRUE ),
    CORROSIVE(50,0.8f, Boolean.TRUE),
    FEROCIOUS(100, 0.9f,  Boolean.TRUE),

    KEY_CARD(200, 1.05f, Boolean.FALSE),

    PROJECTILE(200,1.05f, Boolean.FALSE),

    DOOR(200, 1.05f, Boolean.FALSE)

    ;

    private final int maxHealth;
    private final float movementSpeed;
    private final float hasHealthbar;

    EntityType(int maxHealth, float movementSpeed, Boolean hasHealthBar) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.hasHealthbar = hasHealthBar
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public float getMovementSpeed() {
        return this.movementSpeed;
    }
}
