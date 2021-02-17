package me.mayhem.game.entity.listener;

import me.mayhem.game.GameManager;
import me.mayhem.game.event.EventManager;

public class EntityDeathListener {

    private final GameManager gameManager;

    public EntityDeathListener(GameManager gameManager) {
        this.gameManager = gameManager;

        EventManager.registerListener(this);
    }
}
