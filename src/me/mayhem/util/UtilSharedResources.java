package me.mayhem.util;

import me.mayhem.util.file.UtilFont;
import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.sounds.UtilSound;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Class used for more efficiently accessing the resources used throughout the game
 * All the resources are obtained using lazy getters. This means they are initially null and upon the first
 * time of calling the getter they are then instantiated and loaded.
 *
 * It also contains a map of all the level images which are loaded asynchronously at the start of the game as to prevent
 * lag when swapping between levels.
 */
public class UtilSharedResources {

    private static final Map<Integer, BufferedImage> LOADED_LEVEL_IMAGES = new HashMap<>();

    private static Sound mainTheme = null;
    private static Sprite background = null;
    private static Sprite logo = null;
    private static Sprite WSAD = null;
    private static Sprite door = null;
    private static Sprite card = null;
    private static Font mainFont = null;
    private static Sprite inGameBackgound = null;

    /**
     *
     * Used to initialize all the variables asynchronously as to reduce the initial loading time wen first called
     *
     */
    public static void init() {
        getWSAD();
        getDoor();
        getKeyCard();
        getInGameBackground();
        loadLevels();
    }

    /**
     *
     * Loads and gets the main theme tune
     *
     * @return {@link Sound} of the main theme with volume .5 and looping
     */
    public static Sound getMainTheme() {
        if (mainTheme == null) {
            mainTheme = UtilSound.loadSoundFromPath("menu/Mainthememusic.wav");

            if (mainTheme != null) {
                mainTheme.setLoop(true);
                mainTheme.setVolume(0.5f);
                mainTheme.play();
            }
        }

        return mainTheme;
    }

    /**
     *
     * Loads and gets the menu pages background image
     *
     * @return Background image as a {@link Sprite}
     */
    public static Sprite getBackground() {
        if (background == null) {
            background = UtilSprite.loadFromPath("menu/otherbackground.jpg");
        }

        return background;
    }

    /**
     *
     * Loads and gets the main game's logo
     *
     * @return The main games logo as a {@link Sprite}
     */
    public static Sprite getLogo() {
        if (logo == null) {
            logo = UtilSprite.loadFromPath("menu/mayhemLogo.png");
        }

        return logo;
    }

    /**
     *
     * Gets the WASD {@link Sprite} for the settings page
     *
     * @return The WASD image as a {@link Sprite}
     */
    public static Sprite getWSAD() {
        if (WSAD == null) {
            WSAD = UtilSprite.loadFromPath("menu/WSAD.png");
        }
        return WSAD;
    }

    /**
     *
     * The exit door for the main game
     *
     * @return The exit door image as a {@link Sprite}
     */
    public static Sprite getDoor() {
        if (door == null) {
            door = UtilSprite.loadFromPath("interactables/doors/DoorClosed.png");
        }

        return door;
    }

    /**
     *
     * The key card image for the main game
     *
     * @return the key card image as a {@link Sprite}
     */
    public static Sprite getKeyCard() {
        if (card == null) {
            card = UtilSprite.loadFromPath("interactables/Keycard/KeyCard.png");
        }

        return card;
    }

    /**
     *
     * Loads and gets font for the menus
     *
     * @return The font for the menus
     */
    public static Font getMainFont() {
        if (mainFont == null) {
            mainFont = UtilFont.loadFont("fonts/FreeSans.ttf");
        }

        return mainFont;
    }

    /**
     *
     * Loads and gets the background for in game screens
     *
     * @return Gets the image for the background as a {@link Sprite}
     */
    public static Sprite getInGameBackground() {
        if (inGameBackgound == null) {
            inGameBackgound = UtilSprite.loadFromPath("gamebackground/space.jpg", 2.0f, 2.0f);
        }

        return inGameBackgound;
    }

    /**
     *
     * Loads all the level images and populates the map storing them by their identifier
     * Works by going through all integers from 0 to 99 and attempting to load the image as a resource
     * If the {@link InputStream} is null then it breaks out of the loop as if one id is missing then no levels past that
     * can be reached in the game and as such loading them would be wasteful.
     *
     */
    private static void loadLevels() {
        for (int i = 0; i < 100; i++) {
            InputStream imageStream = UtilSharedResources.class.getClassLoader().getResourceAsStream("levels/level-" + i + ".png");

            if (imageStream == null) {
                return;
            }

            try {
                LOADED_LEVEL_IMAGES.put(i, ImageIO.read(imageStream));
            } catch (IOException e) {
                return;
            }
        }
    }

    /**
     *
     * Gets the image for the level with the id specified
     *
     * @param level The id of the level
     * @return The image for that level
     */
    public static BufferedImage getLevelImage(int level) {
        return LOADED_LEVEL_IMAGES.get(level);
    }
}
