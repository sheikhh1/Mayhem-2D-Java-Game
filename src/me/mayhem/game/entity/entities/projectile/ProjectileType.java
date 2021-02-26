package me.mayhem.game.entity.entities.projectile;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum ProjectileType {

    WEAK(1, 1, "projectiles/greenfireball.png"),
    NORMAL(2, 2, "projectiles/bluefireball.png"),
    STRONG(3, 3, "projectiles/orangefireball.PNG"),
    SUPER(4, 5, "projectiles/redfireball.PNG")
    ;

    private final int index;
    private final int damage;
    private final String imagePath;
    private final Texture projectileTexture;

    ProjectileType(int index, int damage, String imagePath) {
        this.index = index;
        this.damage = damage;
        this.projectileTexture = UtilImageLoader.loadTextureFromStream(imagePath);
        this.imagePath = imagePath;
    }

    private int getIndex() {
        return this.index;
    }

    private int getDamage() {
        return this.damage;
    }

    private Texture getTexture() {
        return this.projectileTexture;
    }

    private String getImagePath() {
        return this.imagePath;
    }



}
