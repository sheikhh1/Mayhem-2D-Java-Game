package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Shape;

public class BouncyBlock extends Block {

    public BouncyBlock(Vector position, Shape drawable, Hitbox hitbox) {
        super(position, drawable, hitbox);
    }

    @Override
    public void onCollide(Entity entity) {
        entity.setState(EntityState.JUMPING);
    }
}
