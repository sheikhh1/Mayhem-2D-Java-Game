package me.mayhem.util.file;

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

    public static Image loadImageFromStream(InputStream inputStream) {
        Image image = new Image();

        try {
            image.loadFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
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
