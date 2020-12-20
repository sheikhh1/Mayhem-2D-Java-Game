package me.mayhem.game.level;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Level {

    //Difficult, Spawner and Layout
    private final List<Entity> entities = new ArrayList<>();

    private final Player player;

    public Level() {
        this.player = this.spawnPlayer();
    }

    private Player spawnPlayer() {
        return null; //TODO:
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Player getPlayer() {
        return this.player;
    }
}
