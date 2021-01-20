package me.mayhem.game.ai.audio;

import org.jsfml.audio.Sound;


public class ShootSound extends AbstractAudioPlayer {
    private Sound sound;

    @Override
    public void play() {
        if (sound == null) {
            this.sound = this.load("audio/shoot.wav");
        }
        sound.play();
    }
}

