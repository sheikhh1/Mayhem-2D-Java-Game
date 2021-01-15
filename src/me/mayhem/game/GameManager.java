package me.mayhem.game;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
import me.mayhem.game.entity.player.state.PlayerState;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.input.InputManager;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

import java.util.Objects;

public class GameManager {

    private final RenderWindow renderWindow;

    private Level currentLevel;
    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;

    public GameManager(RenderWindow renderWindow) {
        this.renderWindow = renderWindow;
        this.currentLevel = new Level(Difficulty.EASY);
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
        this.currentLevel.getPlayer().update(this.renderWindow);
        this.currentLevel.getLayout().draw(this.renderWindow);
    }

    /**
     *
     * Update entities after drawing and user input complete
     *
     */
    public void tick() {
        this.handleEntityCollisions();
        this.handleBlockCollisions();

        Player player = this.currentLevel.getPlayer();
        player.tick();

        if (this.isOffScreen(player)) {
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

            if (this.isOffScreenX(player)) {
                player.getMotion().setX(0);
                player.setState(PlayerState.STANDING);
            }

            if (this.isOffScreenY(player)) {
                player.getMotion().setY(0);
                player.setState(PlayerState.NO_MOTION);
            }

            this.currentLevel.getLayout().moveBlocks(movement);
        }

        this.handleEntityVelocity();
    }

    private void handleEntityCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {
            for (Entity other : this.currentLevel.getEntities()) {
                if (Objects.equals(entity, other)) {
                    continue;
                }

                if (entity.getHitbox().checkForCollision(other.getHitbox())) {
                    entity.getMotion().set(Vector.ZERO);
                    other.getMotion().set(Vector.ZERO);

                    //TODO: call entity collision event
                }
            }
        }
    }

    private void handleBlockCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {

            //TODO: calculate block collisions
        }
    }

    private void handleEntityVelocity() {
        for (Entity entity : this.currentLevel.getEntities()) {
            if (this.isOffScreen(entity)) {
                this.fixEntityMotion(entity);
            }

            entity.getPosition().add(entity.getMotion());
            entity.getMotion().set(0, 0);
        }
    }

    private boolean isOffScreen(Entity entity) {
        return this.isOffScreenX(entity) || this.isOffScreenY(entity);
    }

    private boolean isOffScreenX(Entity entity) {
        return this.isOffScreenLeftX(entity.getPosition(), entity.getMotion(), 0)
                || this.isOffScreenRightX(entity.getPosition(), entity.getMotion(), (int) entity.getWidth());
    }

    private boolean isOffScreenRightX(Vector position, Vector motion, int width) {
        return (position.getX() + width + motion.getX()) > Mayhem.SCREEN_WIDTH;
    }

    private boolean isOffScreenLeftX(Vector position, Vector motion, int width) {
        return (position.getX() + width + motion.getX()) < 0;
    }

    private boolean isOffScreenY(Entity entity) {
        return  this.isOffScreenTopY(entity.getPosition(), entity.getMotion(), 0) || this.isOffScreenBottomY(entity.getPosition(), entity.getMotion(), (int) entity.getHeight());
    }

    private boolean isOffScreenBottomY(Vector position, Vector motion, int height) {
        return (position.getY() + height + motion.getY()) > Mayhem.SCREEN_HEIGHT;
    }

    private boolean isOffScreenTopY(Vector position, Vector motion, int height) {
        return (position.getY() + height + motion.getY()) < 0;
    }

    private void fixEntityMotion(Entity entity) {
        if (this.isOffScreenRightX(entity.getPosition(), entity.getMotion(), 0)) {
            float position = entity.getPosition().getX() + entity.getMotion().getX();
            entity.getMotion().add(position, 0);
        }

        if (this.isOffScreenLeftX(entity.getPosition(), entity.getMotion(), (int) entity.getWidth())) {
            float position = (-1) * (entity.getPosition().getX() + entity.getWidth() + entity.getMotion().getX() - Mayhem.SCREEN_WIDTH);
            entity.getMotion().add(position, 0);
        }

        if (this.isOffScreenBottomY(entity.getPosition(), entity.getMotion(), (int) entity.getHeight())) {
            float position = (-1) * (entity.getPosition().getY() + entity.getHeight() + entity.getMotion().getY() - Mayhem.SCREEN_HEIGHT);
            entity.getMotion().add(0, position);
        }

        if (this.isOffScreenTopY(entity.getPosition(), entity.getMotion(), 0)) {
            float position = entity.getPosition().getY() + entity.getMotion().getY();
            entity.getMotion().add(0, position);
        }
    }
}
