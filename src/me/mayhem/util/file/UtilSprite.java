package me.mayhem.util.file;

import me.mayhem.Mayhem;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class UtilSprite {

    public static Sprite loadFromPath(String path) {
        return loadFromPath(path, 1.0f, 1.0f);
    }

    public static Sprite loadFromPath(String path, float scaleX, float scaleY) {
        Texture newTexture = new Texture();

        try {
            newTexture.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            Sprite sprite = new Sprite(newTexture);

            sprite.scale(scaleX, scaleY);
            return sprite;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
