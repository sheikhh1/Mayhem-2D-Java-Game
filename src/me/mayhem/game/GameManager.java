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
        playerMousePress = new PlayerMousePressListener();
        playerKeyPress = new PlayerKeyboardPressListener(myPlayer);
        playerKeyRelease = new PlayerKeyboardReleaseListener(myPlayer);


    }

    /**
     *
     * Remove registered listeners when user leaves game screen
     *
     */
    public void deinitialize() {
        InputManager.unregisterInput(playerMousePress);
        InputManager.unregisterInput(playerKeyPress);
        InputManager.unregisterInput(playerKeyRelease);

    }

    /**
     *
     * Update drawables
     *
     */
    public void tick() {
        myPlayer.update(renderWindow);
    }

}
