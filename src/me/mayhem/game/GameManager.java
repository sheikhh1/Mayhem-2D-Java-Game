package me.mayhem.game;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.audio.impl.GameStartSound;
import me.mayhem.game.ai.audio.impl.JumpSound;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.event.impl.PlayerCollisionListener;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.game.PlayerEnemyCollideListener;
import me.mayhem.game.entity.player.listeners.input.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.input.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.input.PlayerMousePressListener;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.event.LevelStartEvent;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.input.InputManager;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.screen.UtilScreen;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameManager {

    private final RenderWindow renderWindow;
    private final Text playerHealth;

    private Level currentLevel;
    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;

    private final List<Drawable> drawnShapes = new CopyOnWriteArrayList<>();

    public GameManager(RenderWindow renderWindow, Difficulty difficulty, String playerName) {
        new GameStartSound();
        new JumpSound();
        EventManager.registerListener(new PlayerCollisionListener());
        EventManager.registerListener(new PlayerEnemyCollideListener());

        this.renderWindow = renderWindow;
        this.currentLevel = new Level(difficulty, playerName);
        EventManager.callEvent(new LevelStartEvent(this.currentLevel.getPlayer(), this.currentLevel));

        this.playerHealth = new Text("PLAYER HEALTH: 0/0", UtilFont.loadFont("fonts/FreeMono.ttf"));
        this.drawnShapes.add(this.playerHealth);

        this.initialize();
    }

    /**
     *
     * Initialize key & mouse listeners on game start up
     *
     */
    public void initialize() {
        this.playerMousePress = new PlayerMousePressListener();
        this.playerKeyPress = new PlayerKeyboardPressListener(this.currentLevel.getPlayer());
        this.playerKeyRelease = new PlayerKeyboardReleaseListener(this.currentLevel.getPlayer());
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
    }

    /**
     *
     * Update drawables
     *
     */
    public void draw() {
        for (Entity entity : this.currentLevel.getEntities()) {
            entity.update(this.renderWindow);
        }

        this.currentLevel.getLayout().draw(this.renderWindow);

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

        Player player = this.currentLevel.getPlayer();

        if (UtilScreen.isOffScreen(player)) {
            int xDiff = 0;
            int yDiff = 0;

            if (player.getPosition().getX() > Mayhem.SCREEN_WIDTH) {
                xDiff = -1;
            } else if (player.getPosition().getX() < 0) {
                xDiff = +1;
            }

            if (player.getPosition().getY() > Mayhem.SCREEN_HEIGHT) {
                yDiff = -1;
            } else if (player.getPosition().getY() < 0) {
                yDiff = +1;
            }

            Vector movement = new Vector(xDiff, yDiff);

            if (UtilScreen.isOffScreenX(player)) {
                player.getMotion().setX(0);
                player.setState(EntityState.STANDING);
            }

            if (UtilScreen.isOffScreenY(player)) {
                player.getMotion().setY(0);
                player.setState(EntityState.NO_MOTION);
            }

            this.currentLevel.getLayout().moveBlocks(movement);
        }

        this.handleEntityVelocity();
        this.playerHealth.setString("PLAYER HEALTH " + player.getHealth() + "/" + player.getType().getMaxHealth());
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
                    }

                    if (this.isLowerThenEntity(entity, block) && !collisionDetected) {
                        if (block.getCenter().getX() > entity.getPosition().getX() && block.getCenter().getX() < (entity.getPosition().getX() + entity.getWidth())) {
                            collisionDetected = true;
                            center.setY(entity.getEntityPhysics().getFallStrength());
                        }
                    }

                    entity.getMotion().subtract(center.getX(), center.getY());
                }
            }

            if (collisionDetected) {
                entity.setState(EntityState.NO_MOTION);
            } else {
                entity.setState(EntityState.FALLING);
            }
        }
    }

    private boolean isLowerThenEntity(Entity entity, Block block) {
        return block.getCenter().getY() > (entity.getPosition().getY() + entity.getHeight());
    }

    private void handleEntityVelocity() {
        for (Entity entity : this.currentLevel.getEntities()) {
            if (UtilScreen.isOffScreen(entity)) {
                UtilScreen.fixEntityMotion(entity);
            }

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
