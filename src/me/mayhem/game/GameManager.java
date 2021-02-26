package me.mayhem.game;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.audio.impl.GameStartSound;
import me.mayhem.game.ai.audio.impl.JumpSound;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.event.impl.PlayerCollisionListener;
import me.mayhem.game.entity.listener.EntityDeathListener;
import me.mayhem.game.entity.listener.PlayerDeathListener;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.game.PlayerEnemyCollideListener;
import me.mayhem.game.entity.player.listeners.input.*;
import me.mayhem.game.entity.player.vaccine.listener.VaccineCollideListener;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.event.LevelStartEvent;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.input.InputManager;
import me.mayhem.save.SaveData;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.screen.UtilScreen;
import me.mayhem.util.time.UtilTime;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameManager {

    private final SaveData saveData;
    private final RenderWindow renderWindow;
    private final Text timerText;
    private final Level currentLevel;

    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;
    private PlayerMouseReleaseListener playerMouseRelease;
    private PlayerLostFocusListener playerLostFocus;

    private final List<Drawable> drawnShapes = new CopyOnWriteArrayList<>();

    public GameManager(RenderWindow renderWindow, SaveData saveData) {
        this.saveData = saveData;

        new GameStartSound();
        new JumpSound();
        new PlayerDeathListener();
        new VaccineCollideListener();
        EventManager.registerListener(new PlayerCollisionListener());
        EventManager.registerListener(new PlayerEnemyCollideListener());
        new EntityDeathListener();

        this.renderWindow = renderWindow;
        this.timerText = new Text();
        this.timerText.setPosition(new Vector2f(Mayhem.SCREEN_WIDTH - 100, 0));
        this.timerText.setColor(Color.WHITE);
        this.timerText.setFont(UtilSharedResources.getMainFont());
        this.drawnShapes.add(timerText);
        this.currentLevel = new Level(saveData.getId(), saveData.getDifficulty(), saveData.getName());

        EventManager.callEvent(new LevelStartEvent(this.currentLevel.getPlayer(), this.currentLevel));

        this.initialize();
    }

    /**
     * Initialize key & mouse listeners on game start up
     */
    public void initialize() {
        this.playerMousePress = new PlayerMousePressListener(this.currentLevel.getPlayer());
        this.playerKeyPress = new PlayerKeyboardPressListener(this.currentLevel.getPlayer());
        this.playerKeyRelease = new PlayerKeyboardReleaseListener(this.currentLevel.getPlayer());
        this.playerMouseRelease = new PlayerMouseReleaseListener(this.currentLevel.getPlayer());
        this.playerLostFocus = new PlayerLostFocusListener(this.currentLevel.getPlayer());
    }

    /**
     * Remove registered listeners when user leaves game screen
     */
    public void deinitialize() {
        InputManager.unregisterInput(this.playerMousePress);
        InputManager.unregisterInput(this.playerKeyPress);
        InputManager.unregisterInput(this.playerKeyRelease);
        InputManager.unregisterInput(this.playerMouseRelease);
        InputManager.unregisterInput(this.playerLostFocus);
        EventManager.clearListeners();
    }

    /**
     * Update drawables
     */
    public void draw() {
        this.currentLevel.getLayout().draw(this.renderWindow);

        Iterator<Entity> iterator = this.currentLevel.getEntities().iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.isDead()) {
                if (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.INFECTED) {
                    if (entity.getDeathAnimateComplete()) {
                        iterator.remove();
                        continue;
                    }
                } else {
                    iterator.remove();
                    continue;
                }

            }

            if (UtilScreen.isOffScreen(entity.getPosition().getX(), entity.getPosition().getY())) {
                continue;
            }

            entity.update(renderWindow);
        }

        for (Drawable drawnShape : this.drawnShapes) {
            this.renderWindow.draw(drawnShape);
        }
    }

    /**
     * Update entities after drawing and user input complete
     */
    public void tick() {
        this.handleEntityCollisions();
        this.handleBlockCollisions();

        for (Entity entity : this.currentLevel.getEntities()) {
            entity.getPathing().updatePosition(entity);
            entity.tick();
        }

        for (Entity projectile : this.currentLevel.getProjectiles()) {
            this.currentLevel.spawnEntity(projectile);
        }


        if (this.currentLevel.getProjectiles().size() > 0) {
            this.currentLevel.clearProjectiles();
        }

        for (Entity vaccine : this.currentLevel.getVaccines()) {
            this.currentLevel.spawnEntity(vaccine);
        }

        if (this.currentLevel.getVaccines().size() > 0) {
            this.currentLevel.clearVaccines();
        }

        this.handleScreenScrolling();
        this.handleEntityVelocity();
        this.updateTimer();
    }

    private void handleEntityCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {
            if (entity.isDead()) {
                continue;
            }

            for (Entity other : this.currentLevel.getEntities()) {
                if (Objects.equals(entity, other) || other.isDead()) {
                    continue;
                }

                if (entity.getHitbox().checkForCollision(other.getHitbox())) {
                    EventManager.callEvent(new EntityCollideEvent(entity, other));
                }
            }
        }
    }

    private void handleBlockCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {
            boolean collisionDetected = false;
            boolean collisionDetectedX = false;

            for (Block block : this.currentLevel.getLayout().getBlocks()) {
                if (entity.getHitbox().checkForCollision(block.getHitbox())) {
                    Vector center = new Vector(0f, 0f);

                    if (entity.getType() == EntityType.PROJECTILE || entity.getType() == EntityType.VACCINE) {
                        entity.setDead(true);
                    }

                    if (entity.inBoundsY(block.getCenter()) && !collisionDetectedX) {
                        if (block.getPosition().getX() > entity.getPosition().getX()) {
                            entity.setAttribute("collidedRight", true);
                            center.setX(+3f);
                        } else {
                            entity.setAttribute("collidedLeft", true);
                            center.setX(-3f);
                        }

                        collisionDetectedX = true;

                        entity.setAttribute("collided", true);
                        block.onCollide(entity);
                    }

                    if (this.isLowerThanEntity(entity, block) && !collisionDetected) {
                        if (block.getCenter().getX() > entity.getPosition().getX() && block.getCenter().getX() < (entity.getPosition().getX() + entity.getWidth())) {
                            collisionDetected = true;
                            center.setY(entity.getEntityPhysics().getFallStrength() * 2);
                            entity.setEntityGrounded(true);
                            block.onCollide(entity);
                        }
                    }

                    if (this.isHigherThanEntity(entity, block)) {
                        if (block.getCenter().getX() > entity.getPosition().getX() && block.getCenter().getX() < (entity.getPosition().getX() + entity.getWidth())) {
                            if (entity.isJumping()) {
                                entity.setJumping(false);
                                entity.setFalling(true);
                                block.onCollide(entity);
                            }
                        }
                    }

                    entity.getMotion().subtract(center.getX(), center.getY());
                }
            }

            if (collisionDetected) {
                entity.setState(EntityState.NO_MOTION);
            } else {
                entity.setState(EntityState.FALLING);
                if (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.INFECTED) {
                    entity.setEntityGrounded(false);
                }
            }
        }
    }

    private boolean isLowerThanEntity(Entity entity, Block block) {
        return block.getCenter().getY() > (entity.getPosition().getY() + entity.getHeight());
    }

    private boolean isHigherThanEntity(Entity entity, Block block) {
        return block.getCenter().getY() < (entity.getPosition().getY() + entity.getHeight());
    }

    private void handleScreenScrolling() {
        Player player = this.currentLevel.getPlayer();

        if (UtilScreen.isOffScreen(player)) {
            Vector screenMotion = new Vector(0, 0);
            Vector playerMotion = this.getFixedEntityMotion(player);

            if ((player.getPosition().getX() + player.getWidth() + playerMotion.getX()) > (Mayhem.SCREEN_WIDTH - UtilScreen.SCREEN_RADIUS)) {
                screenMotion.setX(Math.min(-1, playerMotion.getX() * -1));
            } else if ((player.getPosition().getX() + playerMotion.getX()) < UtilScreen.SCREEN_RADIUS) {
                screenMotion.setX(Math.max(1, playerMotion.getX() * -1));
            }

            if ((player.getPosition().getY() + player.getHeight() + playerMotion.getY()) > (Mayhem.SCREEN_HEIGHT - UtilScreen.SCREEN_RADIUS)) {
                screenMotion.setY(Math.min(-1, playerMotion.getY() * -1));
            } else if ((player.getPosition().getY() + playerMotion.getY()) < UtilScreen.SCREEN_RADIUS) {
                screenMotion.setY(Math.max(1, playerMotion.getY() * -1));
            }

            for (Entity entity : this.currentLevel.getEntities()) {
                if (entity instanceof Player) {
                    continue;
                }

                entity.getPosition().add(screenMotion);
            }

            this.currentLevel.getPlayer().getPosition().add(screenMotion);
            this.currentLevel.getLayout().moveBlocks(screenMotion);
        }
    }

    private void handleEntityVelocity() {
        for (Entity entity : this.currentLevel.getEntities()) {
            Vector motionToAdd = this.getFixedEntityMotion(entity);

            entity.getMotion().subtract(motionToAdd);
            entity.getPosition().add(motionToAdd);
            entity.getMotion().set(0, 0);
        }
    }

    private Vector getFixedEntityMotion(Entity entity) {
        Vector motionToAdd = entity.getMotion().clone();

        if (Math.abs(motionToAdd.getX()) > EntityPhysics.MAX_SPEED) {
            motionToAdd.setX((motionToAdd.getX() / Math.abs(motionToAdd.getX())) * EntityPhysics.MAX_SPEED);
        }

        if (Math.abs(motionToAdd.getY()) > EntityPhysics.MAX_FALL_SPEED) {
            motionToAdd.setY((motionToAdd.getY() / Math.abs(motionToAdd.getY())) * EntityPhysics.MAX_FALL_SPEED);
        }

        return motionToAdd;
    }

    private void updateTimer() {
        this.timerText.setString(UtilTime.formatTime(this.currentLevel.getElapsedTime()));
    }

    public void shutdownLevel() {
        this.currentLevel.getEntities().clear();
        this.currentLevel.getLayout().getBlocks().clear();
    }

    public SaveData getSaveData() {
        return this.saveData;
    }
}
