package me.mayhem.util;

import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.sounds.UtilSound;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Sprite;

public class UtilSharedResources {

    private static Sound mainTheme = null;
    private static Sprite background = null;
    private static Sprite logo = null;

    public static Sound getMainTheme() {
        if (mainTheme == null) {
            mainTheme = UtilSound.loadSoundFromPath("menu/Mainthememusic.wav");

            if (mainTheme != null) {
                mainTheme.setLoop(true);
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
            logo = UtilSprite.loadFromPath("menu/mayhemLogo.jpg");
        }

        return background;
    }
}
