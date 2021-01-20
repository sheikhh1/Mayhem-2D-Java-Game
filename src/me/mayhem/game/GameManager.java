package me.mayhem.game;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.input.InputManager;
import me.mayhem.util.Vector;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameManager {

    private final RenderWindow renderWindow;

    private Level currentLevel;
    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;

    private List<RectangleShape> debugShapes = new CopyOnWriteArrayList<>();
    private List<VertexArray> debugShapes2 = new CopyOnWriteArrayList<>();
    private boolean stopMotion = false;

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

        for (RectangleShape debugShape : this.debugShapes) {
            this.renderWindow.draw(debugShape);
        }

        for (VertexArray debugShape : this.debugShapes2) {
            this.renderWindow.draw(debugShape);
        }
    }

    /**
     *
     * Update entities after drawing and user input complete
     *
     */
    public void tick() {
        Player player = this.currentLevel.getPlayer();
        player.tick();

        this.handleEntityCollisions();
        this.handleBlockCollisions();

//        if (UtilScreen.isOffScreen(player)) {
//            int xDiff = 0;
//            int yDiff = 0;
//
//            if (player.getPosition().getX() > Mayhem.SCREEN_WIDTH) {
//                xDiff = -1;
//            } else if (player.getPosition().getX() < 0) {
//                xDiff = +1;
//            }
//
//            if (player.getPosition().getY() > Mayhem.SCREEN_HEIGHT) {
//                yDiff = -1;
//            } else if (player.getPosition().getY() < 0) {
//                yDiff = +1;
//            }
//
//            Vector movement = new Vector(xDiff, yDiff);
//
//            if (UtilScreen.isOffScreenX(player)) {
//                player.getMotion().setX(0);
//                player.setState(PlayerState.STANDING);
//            }
//
//            if (UtilScreen.isOffScreenY(player)) {
//                player.getMotion().setY(0);
//                player.setState(PlayerState.NO_MOTION);
//            }
//
//            this.currentLevel.getLayout().moveBlocks(movement);
//        }

        this.handleEntityVelocity();
    }

    private void handleEntityCollisions() {
        for (Entity entity : this.currentLevel.getEntities()) {
            for (Entity other : this.currentLevel.getEntities()) {
                if (Objects.equals(entity, other)) {
                    continue;
                }

                if (entity.getHitbox().checkForCollision(other.getHitbox())) {
/*                    entity.getMotion().set(Vector.ZERO);
                    other.getMotion().set(Vector.ZERO);*/

                    //TODO: call entity collision event
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
                    FloatRect collision = entity.getHitbox().getCollision(block.getHitbox());
                    Vector center = new Vector(collision.left + (collision.width / 2), collision.top + collision.height / 2);

                    if ((block.getCenter().getY() < (entity.getPosition().getY() + entity.getHeight()) &&
                            block.getCenter().getY() > (entity.getPosition().getY())) && !collisionDetectedX) {
                        if (block.getPosition().getX() > entity.getPosition().getX()) {
                            center.setX(+3f);
                        } else {
                            center.setX(-3f);
                        }

                        collisionDetectedX = true;
                    } else {
                        center.setX(0);
                    }

                    if (block.getCenter().getY() > (entity.getPosition().getY() + entity.getHeight()) && !collisionDetected) {
                        if (block.getCenter().getX() > entity.getPosition().getX()) {
                            collisionDetected = true;
                            RectangleShape shape = new RectangleShape();

                            shape.setSize(new Vector2f(4, 4));
                            shape.setPosition(block.getCenter().toVector());
                            shape.setFillColor(Color.GREEN);

                            this.debugShapes.add(shape);
                            System.out.println("TEST");
                            center.setY(entity.getEntityPhysics().getFallStrength());
                        } else {
                            center.setY(0);
                        }
                    } else {
                        center.setY(0);
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

    private void handleEntityVelocity() {
        if (stopMotion) {
            return;
        }

        for (Entity entity : this.currentLevel.getEntities()) {
//            if (UtilScreen.isOffScreen(entity)) {
//                UtilScreen.fixEntityMotion(entity);
//            }

            entity.getPosition().add(entity.getMotion());
            entity.getMotion().set(0, 0);
        }
    }

}
