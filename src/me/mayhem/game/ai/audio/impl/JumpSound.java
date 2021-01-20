package me.mayhem.game.ai.audio.impl;

import me.mayhem.game.ai.audio.AbstractAudioPlayer;
import me.mayhem.game.entity.player.event.PlayerJumpEvent;
import me.mayhem.game.event.struct.EventListener;
import org.jsfml.audio.Sound;

public class JumpSound extends AbstractAudioPlayer {

    private static final String JUMP_SOUND_PATH = "audio/jump.wav";

    private Sound sound;

    public JumpSound() {
        super();

        this.sound = this.load(JUMP_SOUND_PATH);
    }

    @EventListener
    public void onPlayerJump(PlayerJumpEvent event) {
        this.play();
    }

    @Override
    public void play() {
        this.sound.play();
    }
}



