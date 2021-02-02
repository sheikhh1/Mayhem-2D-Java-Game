package me.mayhem.game.ai.audio;

import me.mayhem.game.event.EventManager;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;

public abstract class AbstractAudioPlayer implements AudioPlayer {

    public AbstractAudioPlayer() {
        EventManager.registerListener(this);
    }

    @Override
    public Sound load(String path) {
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromStream(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        Sound newSound = new Sound();
        newSound.setVolume(5);
        newSound.setBuffer(soundBuffer);

        return newSound;
    }
}
