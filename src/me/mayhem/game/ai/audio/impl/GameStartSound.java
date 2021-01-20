package me.mayhem.game.ai.audio.impl;

import me.mayhem.game.ai.audio.AbstractAudioPlayer;
import me.mayhem.game.event.struct.EventListener;
import me.mayhem.game.level.event.LevelStartEvent;
import org.jsfml.audio.Sound;

public class GameStartSound extends AbstractAudioPlayer {

    private static final String GAME_START_SOUND_PATH = "audio/gamestart.wav";

    private Sound sound;

    public GameStartSound() {
        super();

        this.sound = this.load(GAME_START_SOUND_PATH);
    }

    @EventListener
    public void onLevelStart(LevelStartEvent event) {
        this.play();
    }

    @Override
    public void play() {
        this.sound.play();
    }
}
