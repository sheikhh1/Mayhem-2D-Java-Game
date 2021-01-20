package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class MoveToPlayerPathing implements Pathing {

    private Level currentLevel;
    private Vector toPlayer;
    private Entity entity;

    public MoveToPlayerPathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        this.entity = entity;
        toPlayer = currentLevel.getPlayer().getPosition().clone().subtract(entity.getPosition());
        toPlayer.setY(0);
        toPlayer.normalize().multiply(EntityPhysics.MAX_SPEED);
        entity.getMotion().add(toPlayer);

        this.determineState();
    }

    private void determineState() {
        if (toPlayer.getX() < 0 ) {
            this.entity.setState(EntityState.BACK);
        } else if (toPlayer.getX() > 0){
            this.entity.setState(EntityState.FORWARD);
        } else if (toPlayer.getX() == 0){
            this.entity.setState(EntityState.STANDING);
        }
    }


}
