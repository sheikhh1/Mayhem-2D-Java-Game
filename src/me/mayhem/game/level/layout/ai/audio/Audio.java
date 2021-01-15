package me.mayhem.game.level.layout.ai.audio;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Audio Class
 * Generates methods to load the audio files
 */

public class Audio {
    try {
        soundBuffer.loadFromFile(Paths.get("sound.wav"));
        System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
    } catch(
    IOException ex) {
        //Something went wrong
        System.err.println("Failed to load the sound:");
        ex.printStackTrace();
    }
}
