package me.mayhem.util.file;

import me.mayhem.Mayhem;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class UtilSprite {

    public static Sprite loadFromPath(String path) {
        Texture newTexture = new Texture();

        try {
            newTexture.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            return new Sprite(newTexture);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
