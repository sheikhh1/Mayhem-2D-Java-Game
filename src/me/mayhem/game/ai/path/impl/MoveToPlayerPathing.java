package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class MoveToPlayerPathing implements Pathing {

    private Level currentLevel;

    public MoveToPlayerPathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        Vector toPlayer = currentLevel.getPlayer().getPosition().clone().subtract(entity.getPosition()).normalize();

        toPlayer.setY(0);
        entity.getMotion().add(toPlayer);
    }
}
