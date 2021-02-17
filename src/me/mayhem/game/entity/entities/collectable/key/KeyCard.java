package me.mayhem.game.entity.entities.collectable.key;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.collectable.Collectable;
import me.mayhem.game.entity.physics.type.HoverPhysics;
import me.mayhem.game.entity.player.Player;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class KeyCard extends Entity implements Collectable {

    private final RectangleShape keyCard;

    public KeyCard(Vector position) {
        super(EntityType.KEY_CARD, position, Vector.getZero(), new SpriteHitbox(position, 31, 31), Pathing.HOVER_PATHING);

        super.entityPhysics = new HoverPhysics(this.getType(), this.getMotion());
        this.keyCard = new RectangleShape(new Vector2f(31, 31));
        this.keyCard.setPosition(position.toVector());
        this.keyCard.setTexture(EntityType.KEY_CARD.getEntityTexture());
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.keyCard.setPosition(this.getPosition().toVector());
        renderWindow.draw(this.keyCard);
    }

    @Override
    public void collected(Player player) {
        this.setDead(true);
    }
}
