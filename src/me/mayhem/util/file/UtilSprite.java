package me.mayhem.util.file;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

/**
 *
 * A static utility class for loading Sprites from the resources and then also scaling them as they're obtained
 * to reduce the amount of code at the higher level of abstraction
 *
 */
public class UtilSprite {

    /**
     *
     * Loads a 1 for 1 scale of the sprite at the path
     * Returns null if the sprite doesn't exist
     *
     * @param path The path given
     * @return The sprite at the file path
     */
    public static Sprite loadFromPath(String path) {
        return loadFromPath(path, 1.0f, 1.0f);
    }


    /**
     *
     * Loads a sprite with the specified scale parameters
     * Returns null if the sprite doesn't exist
     *
     * @param path The path given
     * @param scaleX how much to scale it in width by
     * @param scaleY how much to scale it in height by
     * @return The sprite at the file path
     */
    public static Sprite loadFromPath(String path, float scaleX, float scaleY) {
        Texture newTexture = UtilImageLoader.loadTextureFromStream(path);

        if (newTexture == null) {
            return null;
        }

        Sprite sprite = new Sprite(newTexture);
        sprite.scale(scaleX, scaleY);

        return sprite;
    }
}
