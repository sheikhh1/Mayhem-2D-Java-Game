package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;

public class HoverPathing implements Pathing {

    private int updateFunctionCounter = 0;

    @Override
    public void updatePosition(Entity entity) {
        //TODO: CHANGE STATES INSTEAD
        this.updateFunctionCounter++;
        if (this.updateFunctionCounter <= 50) {
            entity.setJumping(true);
            entity.setFalling(false);
        } else {
            entity.setJumping(false);
            entity.setFalling(true);
        }

        if (this.updateFunctionCounter == 100) {
            this.updateFunctionCounter = 0;
        }

    }
}
