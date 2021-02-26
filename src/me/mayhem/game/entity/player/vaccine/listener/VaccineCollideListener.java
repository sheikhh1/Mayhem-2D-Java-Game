package me.mayhem.game.entity.player.vaccine.listener;

import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.player.vaccine.Vaccine;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;

/**
 *
 * A listener class used to damage any entities that are hit by the vaccine projectile
 *
 */
public class VaccineCollideListener {

    /**
     *
     * Default constructor taking no parameters and registering this object as a listener to the {@link EventManager}
     *
     */
    public VaccineCollideListener() {
        EventManager.registerListener(this);
    }

    @EventListener
    public void onEntityCollideEvent(EntityCollideEvent event) {
        if (!(event.getFirst() instanceof Vaccine) && !(event.getSecond() instanceof Vaccine)) {
            return;
        }

        if (event.getFirst() instanceof Vaccine) {
            event.getSecond().damage(event.getFirst(), 10);
        } else if (event.getSecond() instanceof Vaccine) {
            event.getFirst().damage(event.getSecond(), 10);
        }
    }
}
