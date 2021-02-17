package me.mayhem.game;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.audio.impl.GameStartSound;
import me.mayhem.game.ai.audio.impl.JumpSound;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.event.impl.PlayerCollisionListener;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.game.PlayerEnemyCollideListener;
import me.mayhem.game.entity.player.listeners.input.*;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.event.LevelStartEvent;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.input.InputManager;
import me.mayhem.util.Vector;
import me.mayhem.util.screen.UtilScreen;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameManager {

    private final RenderWindow renderWindow;

    private Level currentLevel;
    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;
    private PlayerMouseReleaseListener playerMouseRelease;
    private PlayerLostFocusListener playerLostFocus;

    private final List<Drawable> drawnShapes = new CopyOnWriteArrayList<>();

    public GameManager(RenderWindow renderWindow, Difficulty difficulty, String playerName) {
        new GameStartSound();
        new JumpSound();
        EventManager.registerListener(new PlayerCollisionListener());
        EventManager.registerListener(new PlayerEnemyCollideListener());

        this.renderWindow = renderWindow;
        this.currentLevel = new Level(difficulty, playerName);
        EventManager.callEvent(new LevelStartEvent(this.currentLevel.getPlayer(), this.currentLevel));

        this.initialize();
    }

    /**
     *
     * Initialize key & mouse listeners on game start up
     *
     */
    public void initialize() {
        this.playerMousePress = new PlayerMousePressListener(this.currentLevel.getPlayer());
        this.playerKeyPress = new PlayerKeyboardPressListener(this.currentLevel.getPlayer());
        this.playerKeyRelease = new PlayerKeyboardReleaseListener(this.currentLevel.getPlayer());
        this.playerMouseRelease = new PlayerMouseReleaseListener(this.currentLevel.getPlayer());
        this.playerLostFocus = new PlayerLostFocusListener(this.currentLevel.getPlayer());
    }

    /**
     *
     * Remove registered listeners when user leaves game screen
     *
     */
    public void deinitialize() {
        InputManager.unregisterInput(this.playerMousePress);
        InputManager.unregisterInput(this.playerKeyPress);
        InputManager.unregisterInput(this.playerKeyRelease);
        InputManager.unregisterInput(this.playerMouseRelease);
        InputManager.unregisterInput(this.playerLostFocus);
    }

    /**
     *
     * Update drawables
     *
     */
    public void draw() {
        this.currentLevel.getLayout().draw(this.renderWindow);

        for (Entity entity : this.currentLevel.getEntities()) {
            if (entity.isDead()) {
                continue;
            }

            entity.update(this.renderWindow);
        }

        for (Drawable drawnShape : this.drawnShapes) {
            this.renderWindow.draw(drawnShape);
        }
    }

    /**
     *
     * Update entities after drawing and user input complete
     *
     */
    public void tick() {
        this.handleEntityCollisions();
        this.handleBlockCollisions();

        for (Entity entity : this.currentLevel.getEntities()) {
            entity.getPathing().updatePosition(entity);
            entity.tick();
        }

        this.handleScreenScrolling();
        this.handleEntityVelocity();
    }

    private void handleEntityCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {
            for (Entity other : this.currentLevel.getEntities()) {
                if (Objects.equals(entity, other)) {
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
                            center.setY(entity.getEntityPhysics().getFallStrength());
                            entity.setEntityGrounded(true);
                            block.onCollide(entity);
                        }
                    }

                    if (this.isHigherThanEntity(entity, block)) {
                        if (block.getCenter().getX() > entity.getPosition().getX() && block.getCenter().getX() < (entity.getPosition().getX() + entity.getWidth())) {
                            if (entity.isJumping())  {
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
                if (entity.getType() == EntityType.PLAYER) {
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

            if ((player.getPosition().getX() + player.getWidth() + player.getMotion().getX()) > (Mayhem.SCREEN_WIDTH - UtilScreen.SCREEN_RADIUS)) {
                screenMotion.setX(-3);
            } else if ((player.getPosition().getX() + player.getMotion().getX()) < UtilScreen.SCREEN_RADIUS) {
                screenMotion.setX(+3);
            }

            if ((player.getPosition().getY() + player.getHeight() + player.getMotion().getY()) > (Mayhem.SCREEN_HEIGHT - UtilScreen.SCREEN_RADIUS)) {
                screenMotion.setY(-3);
            } else if ((player.getPosition().getY() + player.getMotion().getY()) < UtilScreen.SCREEN_RADIUS) {
                screenMotion.setY(+3);
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
            Vector motionToAdd = entity.getMotion().clone();

            if (Math.abs(motionToAdd.getX()) > EntityPhysics.MAX_SPEED) {
                motionToAdd.setX((motionToAdd.getX() / Math.abs(motionToAdd.getX())) * EntityPhysics.MAX_SPEED);
            }

            if (Math.abs(motionToAdd.getY()) > EntityPhysics.MAX_FALL_SPEED) {
                motionToAdd.setY((motionToAdd.getY() / Math.abs(motionToAdd.getY())) * EntityPhysics.MAX_FALL_SPEED);
            }

            entity.getMotion().subtract(motionToAdd);

            entity.getPosition().add(motionToAdd);
            entity.getMotion().set(0, 0);
        }
    }

    public void shutdownLevel() {
        this.currentLevel.getEntities().clear();
        this.currentLevel.getLayout().getBlocks().clear();
    }
}
