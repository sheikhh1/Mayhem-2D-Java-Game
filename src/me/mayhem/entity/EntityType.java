package me.mayhem.entity;

public enum EntityType {

    BOSS("Boss", 20),

    HELLO("Hello", 20),
    PLAYER("Player", 200);

    private String name;
    private double maxHealth;

    EntityType(String name, double maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return this.name;
    }
}
