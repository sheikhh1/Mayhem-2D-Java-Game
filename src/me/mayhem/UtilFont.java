package me.mayhem;

import org.jsfml.graphics.Font;

import java.io.IOException;

public class UtilFont {

    public static Font loadFont(String font) {
        Font loadedFont = new Font();

        try {
            loadedFont.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(font));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedFont;
    }

}
