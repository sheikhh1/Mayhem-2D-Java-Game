package me.mayhem.game.entity.entities.projectiles;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.ShapeHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Shape;

public class Projectile extends Entity {

    public Projectile(Shape circle, Vector position) {
        super(EntityType.PROJECTILE, position, Vector.getZero(), new ShapeHitbox(circle, position, 20, 20), Pathing.NO_PATHING);

        this.getEntityPhysics().setEntityMotion(this.getMotion());
    }

    public void tick() {
        //TODO: Projectile Movement Calculation
    }
}
