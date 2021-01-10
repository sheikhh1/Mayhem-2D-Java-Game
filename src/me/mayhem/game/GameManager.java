package me.mayhem.game;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
import me.mayhem.input.InputManager;
import me.mayhem.math.Vector;
import org.jsfml.graphics.RenderWindow;

public class GameManager {

    private final RenderWindow renderWindow;
    private Player myPlayer = new Player("test", new Vector(100,100));
    private PlayerMousePressListener playerMousePress;
    private PlayerKeyboardPressListener playerKeyPress;
    private PlayerKeyboardReleaseListener playerKeyRelease;


    public GameManager(RenderWindow renderWindow) {
        this.renderWindow = renderWindow;
    }

    /**
     *
     * Initialize key & mouse listeners on game start up
     *
     */
    public void initialize() {
        this.playerMousePress = new PlayerMousePressListener();
        this.playerKeyPress = new PlayerKeyboardPressListener(this.myPlayer);
        this.playerKeyRelease = new PlayerKeyboardReleaseListener(this.myPlayer);


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
    public void tick() {
        this.myPlayer.update(this.renderWindow);
    }

}
