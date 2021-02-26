package me.mayhem.util.file;

import me.mayhem.Mayhem;
import org.jsfml.graphics.Font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * A static utility class for loading, and caching, fonts from the given resource path
 *
 */
public class UtilFont {

    private static final Map<String, Font> FONT_CACHE = new HashMap<>();

    /**
     *
     * Loads the font from the given path.
     * It is first checked if the font has already been loaded using the {@link UtilFont#FONT_CACHE} map. If it has been then
     * it returns the cached value
     * If it has not been loaded already then the font is attempted to be loaded from the resource path and then
     * stored in the cache if not null.
     * Returns null if it cannot find the Font at the path
     *
     * @param font The font to load/get
     * @return The font found at the location specified
     */
    public static Font loadFont(String font) {
        Font cachedFont = FONT_CACHE.get(font);

        if (cachedFont != null) {
            return cachedFont;
        }

        Font loadedFont = new Font();

        try {
            loadedFont.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(font));
            FONT_CACHE.put(font, loadedFont);
            return loadedFont;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
