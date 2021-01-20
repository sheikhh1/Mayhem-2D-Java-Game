package me.mayhem.game.ai.audio;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

public class GameStartSound implements AudioPlayer{
    private Sound sound;

    @Override
    public void play() {
        if (sound == null) {
            load("audio/gamestart.wav");
        }
        sound.play();
    }


    @Override
    public Sound load(String path) {


        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get(path));
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        Sound newSound = new Sound();
        newSound.setBuffer(soundBuffer);

        return newSound;
    }

}



