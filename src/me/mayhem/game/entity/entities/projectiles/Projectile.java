package me.mayhem.game.entity.entities.projectiles;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.ShapeHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.physics.type.NoMotionPhysics;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class Projectile<Circle> extends Entity {
    private final CircleShape circle;

    public Projectile(Shape circle, Vector position, Circle circle1) {
        super(EntityType.PROJECTILE, position, Vector.getZero(), new ShapeHitbox(circle, position, 20, 20), Pathing.FORWARD_PATHING);
        this.circle = (CircleShape) circle1;
        this.setState(EntityState.FORWARD);

    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.circle.setPosition(this.getPosition().toVector());
        renderWindow.draw((Drawable) this.circle);
    }

    public void tick() {
        //TODO: Projectile Movement Calculation//
    }
}
