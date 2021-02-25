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

public class UtilSharedResources {

    private static final Map<Integer, BufferedImage> LOADED_LEVEL_IMAGES = new HashMap<Integer, BufferedImage>();

    private static Sound mainTheme = null;
    private static Sprite background = null;
    private static Sprite logo = null;
    private static Sprite WSAD = null;
    private static Sprite door = null;
    private static Sprite card = null;
    private static Font mainFont = null;
    private static Sprite inGameBackgound = null;

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

    public static Sprite getBackground() {
        if (background == null) {
            background = UtilSprite.loadFromPath("menu/otherbackground.jpg");
        }

        return background;
    }

    public static Sprite getLogo() {
        if (logo == null) {
            logo = UtilSprite.loadFromPath("menu/mayhemLogo.png");
        }

        return logo;
    }

    public static Sprite getWSAD() {
        if (WSAD == null) {
            WSAD = UtilSprite.loadFromPath("menu/WSAD.png");
        }
        return WSAD;
    }

    public static Sprite getDoor() {
        if (door == null) {
            door = UtilSprite.loadFromPath("interactables/doors/DoorClosed.png");
        }

        return door;
    }

    public static Sprite getKeyCard() {
        if (card == null) {
            card = UtilSprite.loadFromPath("interactables/Keycard/KeyCard.png");
        }

        return card;
    }

    public static Font getMainFont() {
        if (mainFont == null) {
            mainFont = UtilFont.loadFont("fonts/FreeSans.ttf");
        }

        return mainFont;
    }

    public static Sprite getInGameBackground() {
        if (inGameBackgound == null) {
            inGameBackgound = UtilSprite.loadFromPath("gamebackground/space.jpg", 2.0f, 2.0f);
        }

        return inGameBackgound;
    }

    public static void loadLevels() {
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

    public static BufferedImage getLevelImage(int level) {
        return LOADED_LEVEL_IMAGES.get(level);
    }
}
