package me.mayhem.game.ai.audio;

import org.jsfml.audio.Sound;

public interface AudioPlayer {

    void play();

    Sound load(String path);
}
