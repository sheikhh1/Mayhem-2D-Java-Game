package me.mayhem.game.entity.player.event;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.struct.Event;

public class PlayerJumpEvent extends Event {

    private final Player player;

    public PlayerJumpEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
