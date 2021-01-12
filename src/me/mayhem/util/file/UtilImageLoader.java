package me.mayhem.util.file;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Class to read and return an image given a specified file path
 */
public class UtilImageLoader {

    public static Image loadImageFromPath(Path path) {
        Image image = new Image();

        try {
            image.loadFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static Image loadImageFromStream(InputStream inputStream) {
        Image image = new Image();

        try {
            image.loadFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
