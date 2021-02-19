package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.entities.collect.key.KeyCard;
import me.mayhem.game.entity.entities.friendly.door.DoorState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.system.Clock;

public class DoorStatePathing implements Pathing {

    private final Clock attackedAnimateClock = new Clock();

    private final Level currentLevel;

    private boolean open = false;

    public DoorStatePathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        if (!this.currentLevel.getPlayer().getInventory().contains(KeyCard.KEY_CARD_ID)) {
            return;
        }

        Vector toPlayer = this.currentLevel.getPlayer().getCenter().subtract(entity.getCenter());

        if (toPlayer.getLengthSquared() < 32000) {
            if (!this.open) {
                this.attackedAnimateClock.restart();
                entity.setTexture(DoorState.OPENING.getDoorTexture());
                this.open = true;
            }

            if (this.attackedAnimateClock.getElapsedTime().asMilliseconds() >= 500) {
                entity.setTexture(DoorState.OPEN.getDoorTexture());
            }
        } else {
            entity.setTexture(DoorState.CLOSED.getDoorTexture());
        }
    }
}
