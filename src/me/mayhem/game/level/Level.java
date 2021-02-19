package me.mayhem.game.level;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.layout.Layout;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Level {

    private final List<Entity> entities = new ArrayList<>();

    private final Player player;
    private final Difficulty difficulty;
    private final EntitySpawner spawner;
    private final Layout layout;
    private final long startTime;

    public Level(Difficulty difficulty, String playerName) {
        this(difficulty, playerName, System.currentTimeMillis());
    }

    public Level(Difficulty difficulty, String playerName, long startTime) {
        this.difficulty = difficulty;
        this.spawner = difficulty.getSpawner();
        this.layout = new Layout(this.difficulty);
        this.player = this.spawnPlayer(this.difficulty.getGenerator().getPlayerSpawnPosition(), playerName);
        this.spawner.spawnEntities(this);
        this.entities.add(this.player);
        this.startTime = startTime;
    }

    private Player spawnPlayer(Vector playerSpawnPosition, String name) {
        return new Player(name, playerSpawnPosition, this);
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
        this.entities.sort(Comparator.comparingInt(e -> EntityType.values().length - e.getType().ordinal()));
    }

    public void spawnObstacle(Entity e) {
        this.entities.add(e);
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

    public long getElapsedTime() {
        return (System.currentTimeMillis() - this.startTime);
    }

    public List<Entity> getNearbyEntities(Entity entity, double distanceSquared) {
        List<Entity> nearby = new ArrayList<>();

        for (Entity otherEntity : this.entities) {
            if (Objects.equals(otherEntity, entity)) {
                continue;
            }

            if (UtilVector.getDistanceSquared(entity.getPosition(), otherEntity.getPosition()) <= distanceSquared) {
                nearby.add(otherEntity);
            }
        }

        return nearby;
    }
}
