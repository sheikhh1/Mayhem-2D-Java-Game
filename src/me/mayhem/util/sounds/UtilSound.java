package me.mayhem.util.sounds;

import me.mayhem.Mayhem;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;

/**
 *
 * Utility class for loading the {@link Sound} objects from specified resource paths
 *
 */
public class UtilSound {

    /**
     *
     * Attempts to load the sound from the specified path into a {@link Sound} using a resource stream
     * Returns null when it cannot find the sound
     *
     * @param path The path specified for the sound file
     * @return The loaded sound
     */
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
