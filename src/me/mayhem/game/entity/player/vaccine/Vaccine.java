package me.mayhem.game.entity.player.vaccine;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class Vaccine extends Entity {

    private final RectangleShape vaccine;
    private int xMotion = 0;

    public Vaccine(Vector position, Vector facing, Attribute<?>... attributes) {
        super(EntityType.VACCINE, position, Vector.getZero(), new SpriteHitbox(position, 20, 40), Pathing.NO_PATHING, attributes);
        this.vaccine = new RectangleShape(new Vector2f(40,20));
        this.vaccine.setPosition(position.toVector());


        if (facing.getX() == 1f) {
            this.xMotion = 2;
            this.vaccine.setTexture(VaccineType.RIGHT.getTexture());
        } else {
            this.xMotion = -2;
            this.vaccine.setTexture(VaccineType.LEFT.getTexture());
        }
    }

    @Override
    public void update(RenderWindow window) {
        this.vaccine.setPosition(this.getPosition().toVector());
        window.draw(this.vaccine);
    }

    public void tick() {
        this.getMotion().add(this.xMotion, 0);
    }
}
