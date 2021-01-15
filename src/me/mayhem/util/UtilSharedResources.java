package me.mayhem.util;

import me.mayhem.util.sounds.UtilSound;
import org.jsfml.audio.Sound;

public class UtilSharedResources {

    private static Sound mainTheme = null;

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

}
