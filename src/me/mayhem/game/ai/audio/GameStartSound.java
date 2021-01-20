package me.mayhem.game.ai.audio;

import org.jsfml.audio.Sound;

public class GameStartSound extends AbstractAudioPlayer {
    private Sound sound;

    @Override
    public void play() {
        if (sound == null) {
            this.sound = this.load("audio/gamestart.wav");
        }
        sound.play();
    }

}



