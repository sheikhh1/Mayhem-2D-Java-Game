package me.mayhem.game.entity.door;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Door extends Entity {

    private final Sprite sprite;

    public Door(Vector position, String image, Attribute<?>... attributes) {
        super(EntityType.DOOR, position, Vector.ZERO, new SpriteHitbox(position, 0, 0), Pathing.NO_PATHING, attributes);

        this.sprite = UtilSprite.loadFromPath(image);
    }

    @Override
    public void update(RenderWindow window) {
        window.draw(this.sprite);
    }
}
