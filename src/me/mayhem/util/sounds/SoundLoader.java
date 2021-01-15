package me.mayhem.util.sounds;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

public class SoundLoader {

    public static Sound loadSoundFromPath(String path){

        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get(path));
        } catch(IOException ex) {

            ex.printStackTrace();
        }

        Sound newSound = new Sound();
        newSound.setBuffer(soundBuffer);

        return newSound;
    }


}
