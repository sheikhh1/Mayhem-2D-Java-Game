package me.mayhem.game.entity.listener;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.event.EntityDeathEvent;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.losescreen.LoseScreenManager;
import org.jsfml.graphics.RenderWindow;

public class PlayerDeathListener {

    public PlayerDeathListener() {
        EventManager.registerListener(this);
    }

    @EventListener
    public void onPlayerDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        RenderWindow window = Mayhem.getMainWindow();
        GameScreenManager currentScreen = (GameScreenManager) Mayhem.getCurrentScreen();

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new LoseScreenManager(window, Mayhem.getCurrentScreen().getSound(), currentScreen));
    }
}
