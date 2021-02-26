package me.mayhem.util.file;

import me.mayhem.Mayhem;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * A static utility class for loading {@link Image} and {@link Texture} from the resource paths specified
 *
 */
public class UtilImageLoader {

    /**
     *
     * Loads the image from the resource path specified
     * Returns null if the resource does not exist
     *
     * @param path The path to check for the resource
     * @return The image found at the path
     */
    public static Image loadImageFromStream(String path) {
        Image image = new Image();

        try {
            image.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Texture loadTextureFromStream(InputStream inputStream) {
        Texture texture = new Texture();

        try {
            texture.loadFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return texture;
    }
}
