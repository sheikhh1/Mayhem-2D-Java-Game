package me.mayhem.util.file;

import me.mayhem.Mayhem;
import org.jsfml.graphics.Font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UtilFont {

    private static final Map<String, Font> FONT_CACHE = new HashMap<>();

    public static Font loadFont(String font) {
        Font cachedFont = FONT_CACHE.get(font);

        if (cachedFont != null) {
            return cachedFont;
        }

        Font loadedFont = new Font();

        try {
            loadedFont.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(font));
            FONT_CACHE.put(font, loadedFont);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedFont;
    }

}
