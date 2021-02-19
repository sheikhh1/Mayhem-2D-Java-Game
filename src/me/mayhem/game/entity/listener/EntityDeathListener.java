package me.mayhem.game.entity.listener;

import me.mayhem.game.entity.event.EntityDamageByEntityEvent;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;

public class EntityDeathListener {

    public EntityDeathListener() {
        EventManager.registerListener(this);
    }

    @EventListener
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getAttacked().getHealth() <= 0) {
            event.getAttacked().restartDeathClock();
            event.getAttacked().setDead(true);
        }
    }
}
