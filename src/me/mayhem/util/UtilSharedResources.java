package me.mayhem.util;

import jdk.jshell.execution.Util;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.sounds.UtilSound;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;

public class UtilSharedResources {

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
    public static Sprite getInGameBackground(){
        if (inGameBackgound == null){
            inGameBackgound = UtilSprite.loadFromPath("");
        }
    }
}
