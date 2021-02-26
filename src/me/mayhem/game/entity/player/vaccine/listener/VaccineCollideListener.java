package me.mayhem.game.entity.player.vaccine.listener;

import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.player.vaccine.Vaccine;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * A listener class used to damage any entities that are hit by the vaccine projectile
 *
 */
public class VaccineCollideListener {

    private static final Set<EntityType> IGNORED_TYPES = new HashSet<>();

    static {
        IGNORED_TYPES.add(EntityType.VACCINE);
        IGNORED_TYPES.add(EntityType.PLAYER);
        IGNORED_TYPES.add(EntityType.DOOR);
        IGNORED_TYPES.add(EntityType.PROJECTILE);
        IGNORED_TYPES.add(EntityType.KEY_CARD);
        IGNORED_TYPES.add(EntityType.SPIKES);
    }


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
            if (IGNORED_TYPES.contains(event.getSecond().getType()) || event.getFirst().isDead()) {
                return;
            }

            event.getSecond().damage(event.getFirst(), 10);
            event.getFirst().setDead(true);
        } else if (event.getSecond() instanceof Vaccine) {
            if (IGNORED_TYPES.contains(event.getFirst().getType()) || event.getFirst().isDead()) {
                return;
            }

            event.getFirst().damage(event.getSecond(), 10);
            event.getSecond().setDead(true);
        }
    }
}
