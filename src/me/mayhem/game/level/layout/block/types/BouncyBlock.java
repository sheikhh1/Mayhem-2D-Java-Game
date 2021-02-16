package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.ShapeHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.physics.EntityPhysics;
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
        entity.getEntityPhysics().setJumpStrength(EntityPhysics.DEFAULT_JUMP_STRENGTH * 2);
        entity.setState(EntityState.JUMPING);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Block.Builder {
        @Override
        public Block build() {
            this.drawable.setFillColor(this.fillColor);
            this.drawable.setOutlineColor(this.outlineColor);
            this.drawable.setPosition(this.position.toVector());

            if (this.hitbox == null) {
                this.hitbox = new ShapeHitbox(this.drawable, this.position, this.height, this.width);
            }

            return new BouncyBlock(this.position, this.drawable, this.hitbox);
        }
    }
}
