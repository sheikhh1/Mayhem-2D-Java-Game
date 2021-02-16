package me.mayhem.game.level.layout.block.texture;

import me.mayhem.util.RGB;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.Sprite;

public enum BlockTexture {

    BASIC(RGB.of(255, 255, 255), UtilSprite.loadFromPath("blocks/default.png", 1.6f, 1.6f)),
    BOUNCY(RGB.of(255, 255, 255), UtilSprite.loadFromPath("blocks/bouncy.png", 1.6f, 1.6f)),
    LAVA(RGB.of(138, 138, 138), UtilSprite.loadFromPath("blocks/lava.png", 1.6f, 1.6f)),

    ;

    private final RGB rgb;
    private final Sprite sprite;

    BlockTexture(RGB rgb, Sprite sprite) {
        this.rgb = rgb;
        this.sprite = sprite;
    }

    public RGB getRgb() {
        return this.rgb;
    }

    public Sprite getSprite() {
        return this.sprite;
    }
}
