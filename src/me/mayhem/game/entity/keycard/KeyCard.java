package me.mayhem.game.entity.keycard;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class KeyCard extends Entity {

    private RectangleShape keyCard;

    public KeyCard(Vector position) {
        super(EntityType.KEYCARD, position, Vector.getZero(), new SpriteHitbox(position, 31, 31), Pathing.HoverPathing);
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.NO_MOTION);
        this.keyCard = new RectangleShape(new Vector2f(31, 31));
        this.keyCard.setPosition(position.toVector());
        this.keyCard.setTexture(EntityType.KEYCARD.getEntityTexture());
    }

    @Override
    public void update(RenderWindow renderWindow) {
       renderWindow.draw(this.keyCard);
    }


}
