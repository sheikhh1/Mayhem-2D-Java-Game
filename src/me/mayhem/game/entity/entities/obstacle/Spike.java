package me.mayhem.game.entity.entities.obstacle;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Spike extends Entity {

    private final Sprite sprite;

    public Spike(Vector position, Level level) {
        super(EntityType.SPIKES, position, Vector.getZero(), new SpriteHitbox(position, 48, 48), Pathing.NO_PATHING);

        sprite = UtilSprite.loadFromPath("obstacles/Spike.png");
        sprite.setPosition(position.toVector());
    }

    @Override
    public void update(RenderWindow window) {
        this.sprite.setPosition(this.getPosition().toVector());
        window.draw(this.sprite);
    }
}
