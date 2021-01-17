package me.mayhem.game.entity;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.animation.EntityAnimation;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

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
    private boolean entityStanding = false;
    private EntityState currentState;
    private EntityState[] states = new EntityState[2];

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
        this.hitbox = new SpriteHitbox(this.getSprite(), this.position, (int) this.getHeight(), (int) this.getWidth());
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

    public boolean isStanding() {
        return this.entityStanding;
    }

    public void setStanding(boolean entityStanding) {
        this.entityStanding = entityStanding;
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

    public Sprite getSprite() {
        return this.animate.getSprite();
    }


    /**
     * Updates position of the Player depending on user input
     * Outputs onto main window
     * @param window
     */
    public void update(RenderWindow window) {
        RectangleShape rectangleShape = new RectangleShape();

        rectangleShape.setPosition(this.position.toVector());
        rectangleShape.setSize(new Vector2f(this.getWidth(), this.getHeight()));
        rectangleShape.setFillColor(Color.GREEN);

        animate.playAnimation(window);
//        window.draw(rectangleShape);
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
    public void setState(EntityState state) {
        if (state == null || (currentState == EntityState.NO_MOTION && state == EntityState.NO_MOTION) ){
            return;
        }

        currentState = this.states[state.getIndex()];

        if (currentState == EntityState.FALLING || currentState == EntityState.JUMPING) {
            if (state == EntityState.NO_MOTION) {
                this.setJumping(false);
                this.setFalling(false);
                this.getEntityPhysics().reset(state);
                this.states[state.getIndex()] = state;
            }
        } else if (currentState == EntityState.NO_MOTION) {
            if (state == EntityState.JUMPING) {
                this.setJumping(true);
                this.setFalling(false);
                this.states[state.getIndex()] = state;
            } else if (state == EntityState.FALLING) {
                this.setFalling(true);
                this.setJumping(false);
                this.states[state.getIndex()] = state;
            }
        } else {
            this.states[state.getIndex()] = state;

            if (state == EntityState.STANDING) {
                animate.setColumn(0);
                animate.setPause(true);
                this.setForward(false);
                this.setBack(false);
            } else if (state == EntityState.BACK) {
                this.setForward(false);
                this.setBack(true);
            } else if (state == EntityState.FORWARD) {
                this.setForward(true);
                this.setBack(false);
            }
        }
    }

    public EntityState getState(int index) {
        return this.states[index];
    }
}
