package me.mayhem.game.entity.player.vaccine;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum VaccineType {

    LEFT(1, "projectiles/LeftSyringe.png"),
    RIGHT(1, "projectiles/RightSyringe.png")
    ;

    private final int damage;
    private final Texture imageTexture;
    private final String imagePath;

    VaccineType(int damage, String imagePath) {
        this.damage = damage;
        this.imageTexture = UtilImageLoader.loadTextureFromStream(imagePath);
        this.imagePath = imagePath;
    }

    private int getDamage() {
        return this.damage;
    }

    private Texture getTexture() {
        return this.imageTexture;
    }
}
