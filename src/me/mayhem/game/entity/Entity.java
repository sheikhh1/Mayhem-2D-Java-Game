package me.mayhem.game.entity;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.RectangleHitbox;
import me.mayhem.game.entity.animation.EntityAnimation;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

import java.util.Arrays;
import java.util.List;

/**
 * Entity Class
 * Used to store all the common information per entity in the game
 */
public class Entity {

    private final EntityType type;
    protected final EntityAnimation animate = new EntityAnimation();

    private Vector position;
    private Vector motion;
    private Pathing pathing;
    private Hitbox hitbox;
    private List<Attribute<?>> attributes;
    private EntityPhysics entityPhysics;
    private boolean entityFall = true;
    private boolean entityForward = false;
    private boolean entityBack = false;
    private boolean entityJump = false;

    /**
     * Entity Constructor
     * @param type - Type of Entity - eg Player/Enemy
     * @param position - Current Position of entity relative to the game window
     * @param motion - Motion of the entity eg if entity is moving
     * @param pathing - Pathing for AI generated movement
     * @param attributes - Attributes an entity has - eg health
     */
    public Entity(EntityType type, Vector position, Vector motion, Pathing pathing, Attribute<?>... attributes) {
        this.type = type;
        this.position = position;
        this.motion = motion;
        this.pathing = pathing;
        this.hitbox = new RectangleHitbox(this.position, (int) this.getHeight(), (int) this.getWidth(),this.animate.getLocalBounds());
        this.attributes = Arrays.asList(attributes);
        this.entityPhysics = new EntityPhysics();
    }

    public EntityType getType() {
        return this.type;
    }

    public Vector getPosition() {
        return this.position;
    }

    public EntityPhysics getEntityPhysics() {
        return this.entityPhysics;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector getMotion() {
        return this.motion;
    }

    public Pathing getPathing() {
        return this.pathing;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public List<Attribute<?>> getAttributes() {
        return this.attributes;
    }

    public Attribute<?> getAttribute(String identifier) {
        for (Attribute<?> attribute : this.attributes) {
            if (attribute.getIdentifier().equals(identifier)) {
                return attribute;
            }
        }

        return null;
    }

    public boolean isJumping() {
        return this.entityJump;
    }

    public void setJumping(boolean entityJump) {
        this.entityJump = entityJump;
    }

    public boolean isForward() {
        return this.entityForward;
    }

    public void setForward(boolean entityForward) {
        this.entityForward = entityForward;
    }

    public boolean isBack() {
        return this.entityBack;
    }

    public void setBack(boolean entityBack) {
        this.entityBack = entityBack;
    }

    public boolean isFalling() {
        return this.entityFall;
    }

    public void setFalling(boolean entityFall) {
        this.entityFall = entityFall;
    }

    public float getHeight() {
        return this.animate.getHeight();
    }

    public float getWidth() {
        return this.animate.getWidth();
    }

    /**
     * Updates position of the Player depending on user input
     * Outputs onto main window
     * @param window
     */
    public void update(RenderWindow window) {
        animate.playAnimation(window);
    }

}
