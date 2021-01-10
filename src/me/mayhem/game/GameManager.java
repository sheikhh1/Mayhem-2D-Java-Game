package me.mayhem.game;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
import me.mayhem.math.Vector;
import org.jsfml.graphics.RenderWindow;

public class GameManager {

    private final RenderWindow renderWindow;
    private Player myPlayer = new Player("test", new Vector(100,100));


    public GameManager(RenderWindow renderWindow) {
        this.renderWindow = renderWindow;
    }


    public void initialize() {
        // Player key listeners
        new PlayerMousePressListener();
        new PlayerKeyboardPressListener(myPlayer);
        new PlayerKeyboardReleaseListener(myPlayer);
    }

    public void deinitialize() {

    }
    
    public void tick() {
        myPlayer.update(renderWindow);
    }

}
