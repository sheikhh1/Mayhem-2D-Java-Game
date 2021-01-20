package me.mayhem.game.ai.audio;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;

public abstract class AbstractAudioPlayer implements AudioPlayer {

    @Override
    public Sound load(String path) {
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromStream(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        Sound newSound = new Sound();
        newSound.setBuffer(soundBuffer);

        return newSound;
    }
}
