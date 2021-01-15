package me.mayhem.util.sounds;

import me.mayhem.Mayhem;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;

public class UtilSound {

    public static Sound loadSoundFromPath(String path) {
        SoundBuffer soundBuffer = new SoundBuffer();
        Sound newSound = new Sound();

        try {
            soundBuffer.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            newSound.setBuffer(soundBuffer);
            return newSound;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
