package me.mayhem.game.entity.event;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.event.struct.Event;

public class EntityDamageByEntityEvent extends Event {

    private final Entity attacked;
    private final Entity attacker;

    private double damage;

    public EntityDamageByEntityEvent(Entity attacked, Entity attacker, double damage) {
        this.attacked = attacked;
        this.attacker = attacker;
        this.damage = damage;
    }

    public Entity getAttacked() {
        return this.attacked;
    }

    public Entity getAttacker() {
        return this.attacker;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
