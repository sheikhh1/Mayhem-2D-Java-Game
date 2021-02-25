package me.mayhem.game.entity.listener;

import me.mayhem.game.entity.event.EntityDamageByEntityEvent;
import me.mayhem.game.entity.event.EntityDeathEvent;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;

public class EntityDeathListener {

    public EntityDeathListener() {
        EventManager.registerListener(this);
    }

    @EventListener
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getAttacked().getHealth() <= 0) {
            event.setDamage(0);

            if (!event.getAttacked().isDead()) {
                event.getAttacked().restartDeathClock();
                EventManager.callEvent(new EntityDeathEvent(event.getAttacked()));
            }

            event.getAttacked().setDead(true);
        }
    }
}
