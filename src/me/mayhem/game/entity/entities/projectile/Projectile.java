package me.mayhem.game.entity.entities.projectile;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.Direction;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Projectile extends Entity implements Enemy {

    private final Sprite sprite;
    private int xMotion = 0;

    public Projectile(Vector position,Vector playerPosition Attribute<?>... attributes) {
        super(EntityType.PROJECTILE, position, Vector.getZero(), new SpriteHitbox(position, 5, 5), Pathing.NO_PATHING, attributes);

        this.sprite = UtilSprite.loadFromPath(EntityType.PROJECTILE.getImagePath());

        if (Direction.LEFT.is(playerPosition, position)) {
            this.xMotion = -1;
        } else {
            this.xMotion = 1;
        }

    }

    @Override
    public void update(RenderWindow window) {
        this.sprite.setPosition(this.getPosition().toVector());
        window.draw(this.sprite);
    }

    @Override
    public void attack(Player player) {
        if (!player.isDead()) {
            player.damage(this, 4);
            this.setDead(true);
        }
    }
}
