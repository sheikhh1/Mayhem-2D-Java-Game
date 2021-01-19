package me.mayhem.game.level.layout.audio;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

public class Audio {
    public void shoot() {
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get("shoot.wav"));
            System.out.println();
        } catch (IOException ex) {
            System.err.println("Failed to load the sound:");
            e.printStackTrace();
        }
    }
    public void backgroundsound(){
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get("backgroundsound.wav"));
            System.out.println();
        } catch (IOException ex) {
            System.err.println("Failed to load the sound:");
            e.printStackTrace();
        }
    }
    public void

}
