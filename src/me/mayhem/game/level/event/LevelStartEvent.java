package me.mayhem.game.level.event;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.struct.Event;
import me.mayhem.game.level.Level;

public class LevelStartEvent extends Event {

    private final Player player;
    private final Level level;

    public LevelStartEvent(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }
}
