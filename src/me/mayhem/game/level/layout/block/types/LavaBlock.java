package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.Direction;
import org.jsfml.graphics.Sprite;

public class LavaBlock extends Block {

    public LavaBlock(Vector position, Sprite sprite, Hitbox hitbox, int width, int height) {
        super(position, sprite, hitbox, width, height);
    }

    @Override
    public void onCollide(Entity entity) {
        if (!Direction.ABOVE.is(entity.getPosition(), this.getPosition())) {
            return;
        }

        entity.damage(entity, 1f);
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

            return new LavaBlock(this.position, BlockTexture.LAVA.getSprite(), this.hitbox, this.width, this.height);
        }
    }
}
