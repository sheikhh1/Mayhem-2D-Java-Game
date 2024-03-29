package me.mayhem.game.entity.player.listeners.game;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.entities.collect.Collectable;
import me.mayhem.game.entity.entities.collect.key.KeyCard;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.entities.friendly.door.Door;
import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.event.struct.EventListener;
import me.mayhem.screens.end.finish.EndGameScreenManager;
import me.mayhem.screens.end.win.WinScreenManager;
import me.mayhem.screens.game.game.GameScreenManager;
import org.jsfml.graphics.RenderWindow;

public class PlayerEnemyCollideListener {

    @EventListener
    public void onPlayerCollideWithEnemy(PlayerCollideWithEntityEvent event) {
        if (event.getEntity() instanceof Enemy) {
            ((Enemy) event.getEntity()).attack(event.getPlayer());
        } else if (event.getEntity() instanceof Collectable) {
            ((Collectable) event.getEntity()).collected(event.getPlayer());
        } else if (event.getEntity() instanceof Door) {
            if (event.getEntity().getCenter().subtract(event.getPlayer().getPosition()).getLengthSquared() > 1000) {
                return;
            }

            if (event.getPlayer().getInventory().contains(KeyCard.KEY_CARD_ID)) {
                RenderWindow window = Mayhem.getMainWindow();
                GameScreenManager currentScreen = (GameScreenManager) Mayhem.getCurrentScreen();

                Mayhem.getCurrentScreen().unloadScreen(window);
                GameScreenManager screen = (GameScreenManager) Mayhem.getCurrentScreen();
                if (screen.getGame().getSaveData().getId() == 3){
                    Mayhem.setCurrentScreen(new EndGameScreenManager(window, Mayhem.getCurrentScreen().getSound(), currentScreen));
                }else {
                    Mayhem.setCurrentScreen(new WinScreenManager(window, Mayhem.getCurrentScreen().getSound(), currentScreen));
                }
            }
        }
    }
}
