package me.mayhem.game.entity.entities.projectile;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Projectile extends Entity {

    private final Sprite sprite;

    public Projectile(Vector position, Vector motion, String image, Attribute<?>... attributes) {
        super(EntityType.PROJECTILE, position, motion, new SpriteHitbox(position, 5, 5), Pathing.NO_PATHING, attributes);

        this.sprite = UtilSprite.loadFromPath(image);
    }

    @Override
    public void update(RenderWindow window) {
        window.draw(this.sprite);
    }
}
