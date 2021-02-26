package me.mayhem.game.entity.entities.projectile;

public enum ProjectileType {

    


    private final int index;
    private final int damage;
    private final String imagePath;

    ProjectileType(int index, int damage, String imagePath) {
        this.index = index;
        this.damage = damage;
        this.imagePath = imagePath;
    }

    private int getIndex() {
        return this.index;
    }


}
