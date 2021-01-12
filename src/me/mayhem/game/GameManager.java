package me.mayhem.game;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
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
        for (Entity entity : this.currentLevel.getEntities()) {
            for (Entity other : this.currentLevel.getEntities()) {
                if (Objects.equals(entity, other)) {
                    continue;
                }

                if (entity.getHitbox().checkForCollision(other.getHitbox())) {
                    entity.getMotion().set(0, 0);
                    other.getMotion().set(0, 0);

                    //TODO: call entity collision event
                }
            }
         }

        for (Entity entity : this.currentLevel.getEntities()) {
            entity.getPosition().add(entity.getMotion());
        }

        Player player = this.currentLevel.getPlayer();

        if (this.isOffScreen(player.getPosition())) {
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

            this.currentLevel.getLayout().moveBlocks(movement);
            player.getPosition().add(movement);
        }
    }

    private boolean isOffScreen(Vector position) {
        return position.getX() < 0 || position.getX() > Mayhem.SCREEN_WIDTH || position.getY() < 0 || position.getY() > Mayhem.SCREEN_HEIGHT;
    }
}
