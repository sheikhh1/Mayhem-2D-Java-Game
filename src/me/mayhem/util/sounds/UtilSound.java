package me.mayhem.util.sounds;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

public class UtilSound {

    public static Sound loadSoundFromPath(String path) {
        SoundBuffer soundBuffer = new SoundBuffer();
        Sound newSound = new Sound();

        try {
            soundBuffer.loadFromFile(Paths.get(path));
            newSound.setBuffer(soundBuffer);
            return newSound;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
