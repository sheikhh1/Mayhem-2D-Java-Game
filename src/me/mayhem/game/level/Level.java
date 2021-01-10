package me.mayhem.game.level;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.layout.Layout;
import me.mayhem.game.level.spawning.EntitySpawner;

import java.util.ArrayList;
import java.util.List;

public class Level {

    // Test Commit

    private final List<Entity> entities = new ArrayList<>();

    private final Player player;
    private final Difficulty difficulty;
    private final EntitySpawner spawner;
    private final Layout layout;

    public Level(Difficulty difficulty) {
        this.player = this.spawnPlayer();
        this.difficulty = difficulty;
        this.spawner = null; //TODO: get from difficulty
        this.layout = null; //TODO: create layer
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

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public EntitySpawner getSpawner() {
        return this.spawner;
    }

    public Layout getLayout() {
        return this.layout;
    }
}
