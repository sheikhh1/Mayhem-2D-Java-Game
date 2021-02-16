package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.Direction;
import org.jsfml.graphics.Sprite;

public class SpeedupLeftBlock extends Block {

    private static final Vector MOVE_LEFT = new Vector(-5, 0);

    public SpeedupLeftBlock(Vector position, Sprite sprite, Hitbox hitbox, int width, int height) {
        super(position, sprite, hitbox, width, height);
    }

    @Override
    public void onCollide(Entity entity) {
        if (!Direction.ABOVE.is(entity.getPosition(), this.getPosition())) {
            return;
        }

        entity.getMotion().add(MOVE_LEFT);
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

            return new SpeedupLeftBlock(this.position, BlockTexture.SPEED_UP_LEFT.getSprite(), this.hitbox, this.width, this.height);
        }
    }
}
