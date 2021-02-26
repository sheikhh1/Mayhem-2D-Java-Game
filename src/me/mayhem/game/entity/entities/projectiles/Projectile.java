package me.mayhem.game.entity.entities.projectiles;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.Direction;
import me.mayhem.util.direction.UtilVector;
import org.jsfml.graphics.*;


public class Projectile extends Entity implements Enemy {
    private final CircleShape circle;
    private int xVelocity = 0;


    public Projectile(Vector position, Vector playerPosition) {
        super(EntityType.PROJECTILE, position, Vector.getZero(), new SpriteHitbox(position, 10, 10), Pathing.NO_PATHING);
        this.circle = new CircleShape(10);
        this.circle.setFillColor(Color.BLUE);
        this.circle.setPosition(position.toVector());

        if (Direction.LEFT.is(playerPosition, position)) {
            xVelocity = 1;
        } else {
            xVelocity = -1;
        }
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.circle.setPosition(this.getPosition().toVector());
        renderWindow.draw(this.circle);
    }

    public void tick() {
        //TODO: Projectile Movement Calculation//
        this.getMotion().add(xVelocity,0);
    }

    @Override
    public void attack(Player player) {
        if (!player.isDead()) {
            player.damage(this, 5);
            this.setDead(true);
        }
    }
}
