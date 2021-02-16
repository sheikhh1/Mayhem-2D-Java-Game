package me.mayhem.game.level.layout.block.types;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Sprite;

public class WallDamageBlock extends Block {

    public WallDamageBlock(Vector position, Sprite sprite, Hitbox hitbox, int width, int height) {
        super(position, sprite, hitbox, width, height);
    }

    @Override
    public void onCollide(Entity entity) {
        //TODO:
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

            return new WallDamageBlock(this.position, BlockTexture.WALL_DAMAGE.getSprite(), this.hitbox, this.width, this.height);
        }
    }
}
