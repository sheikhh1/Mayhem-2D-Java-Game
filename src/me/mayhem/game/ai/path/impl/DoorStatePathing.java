package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.Level;

public class DoorStatePathing implements Pathing {

    private Level currentLevel;

    public DoorStatePathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {

    }
}
