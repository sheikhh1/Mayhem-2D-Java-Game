package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.door.DoorEnum;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class DoorStatePathing implements Pathing {

    private Level currentLevel;

    public DoorStatePathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        Vector toPlayer = this.currentLevel.getPlayer().getPosition().clone().subtract(entity.getPosition());

        if (toPlayer.getLengthSquared() < 8000) {
            entity.setTexture(DoorEnum.OPEN.getDoorTexture());
        } else if (toPlayer.getLengthSquared() < 8000) {
            entity.setTexture(DoorEnum.OPENING.getDoorTexture());
        } else {
            entity.setTexture(DoorEnum.CLOSED.getDoorTexture());

        }
    }
}
