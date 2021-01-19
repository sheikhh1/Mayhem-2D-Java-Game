package me.mayhem.game.ai.audio;
import org.jsfml.audio.SoundBuffer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.nio.file.Paths;

public class Audio {

/**
 * Audio Class
 * Generates methods to load the audio files
 */
    AudioInputStream soundStream;
    SoundBuffer soundBuffer;

    public void completedgame(){
        try {

            soundBuffer.loadFromFile(Paths.get("sound.wav"));
            System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
        } catch (IOException ex) {
            //Something went wrong
            System.err.println("Failed to load the sound:");
            ex.printStackTrace();
        }
    }

}
