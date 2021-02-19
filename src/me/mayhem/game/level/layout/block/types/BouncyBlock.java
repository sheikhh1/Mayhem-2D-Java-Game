package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Sprite;

public class BouncyBlock extends Block {

    public BouncyBlock(Vector position, Sprite sprite, Hitbox hitbox, int width, int height) {
        super(position, sprite, hitbox, width, height);
    }

    @Override
    public void onCollide(Entity entity) {
        entity.getEntityPhysics().setJumpStrength(entity.getType().getJumpStrength() * 2);
        entity.setState(EntityState.JUMPING);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Block.Builder {
        @Override
        public Block build() {
            if (this.hitbox == null) {
                this.hitbox = new SpriteHitbox(this.position, this.height, this.width);
            }

            return new BouncyBlock(this.position, BlockTexture.BOUNCY.getSprite(), this.hitbox, this.width, this.height);
        }
    }
}
