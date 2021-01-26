package me.mayhem.game.collision.doors;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

import static java.awt.SystemColor.window;

public class Door extends Entity {
    private Sprite sprite;

    public Door(EntityType type, Vector position, Vector motion, String image, Hitbox hitbox, Pathing pathing, Attribute<?>... attributes) {
        super(EntityType.DOOR, position, Vector.ZERO, new SpriteHitbox(position, 0, 0), Pathing.NO_PATHING, attributes);

        this.sprite = this.loadFromPath(image);

        @Override
        public void update(RenderWindow window) {
            window.draw(this.sprite);
        }
}
