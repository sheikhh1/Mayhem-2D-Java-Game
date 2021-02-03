package me.mayhem.game.entity.obstacles;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Obstacle extends Entity {

    private final Sprite sprite;

    public Obstacle(Vector position, Level level) {
        super(EntityType.SPIKES, position, Vector.getZero(), new SpriteHitbox(position, 48,48 ), Pathing.NO_PATHING);

        sprite = UtilSprite.loadFromPath("obstacles/Spike.png");
        sprite.setPosition(position.toVector());

        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.NO_MOTION);
    }

    @Override
    public Vector getMotion() {
        return Vector.getZero();
    }

    @Override
    public void update(RenderWindow r) {
        r.draw(this.sprite);
    }
}
