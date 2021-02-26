package me.mayhem.game.level;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.event.EntitySpawnEvent;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.except.LevelLoadingException;
import me.mayhem.game.level.layout.Layout;
import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Level {

    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> projectiles = new ArrayList<>();
    private final List<Entity> vaccines = new ArrayList<>();

    private final Player player;
    private final Layout layout;
    private final long startTime;

    public Level(int levelId, Difficulty difficulty, String playerName) {
        this(levelId, difficulty, playerName, System.currentTimeMillis());
    }

    public Level(int levelId, Difficulty difficulty, String playerName, long startTime) {
        EntitySpawner spawner = difficulty.getSpawner();

        LevelGenerator levelGenerator = LevelGenerator.preDefined(levelId);

        if (levelGenerator == null) {
            throw new LevelLoadingException(levelId + "", "Failed to find level generator");
        }

        this.layout = new Layout(levelGenerator);
        this.player = this.spawnPlayer(levelGenerator.getPlayerSpawnPosition(), playerName);

        spawner.spawnEntities(this, levelGenerator);

        this.entities.add(this.player);
        this.startTime = startTime;
    }

    private Player spawnPlayer(Vector playerSpawnPosition, String name) {
        return new Player(name, playerSpawnPosition, this);
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void spawnProjectile(Entity entity) {
        this.projectiles.add(entity);
    }

    public List<Entity> getProjectiles() {
        return this.projectiles;
    }

    public void clearProjectiles() {
        this.projectiles.clear();
    }

    public void spawnVaccine(Entity entity) {
        this.vaccines.add(entity);
    }

    public void spawnEntity(Entity entity) {
        EntitySpawnEvent event = new EntitySpawnEvent(entity, this);

        EventManager.callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        this.entities.add(entity);
        this.entities.sort(Comparator.comparingInt(e -> EntityType.values().length - e.getType().ordinal()));
    }

    public Player getPlayer() {
        return this.player;
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
