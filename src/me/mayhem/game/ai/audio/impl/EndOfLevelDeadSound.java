package me.mayhem.game.ai.audio.impl;

import me.mayhem.game.ai.audio.AbstractAudioPlayer;
import org.jsfml.audio.Sound;

public class EndOfLevelDeadSound extends AbstractAudioPlayer {
    private Sound sound;

    @Override
    public void play() {
        if (sound == null) {
            this.sound = this.load("audio/endofleveldead.wav");
        }
        sound.play();
    }

}


