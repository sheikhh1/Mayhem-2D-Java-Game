package me.mayhem.game.entity;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.attribute.AttributeFactory;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.entity.animation.EntityAnimation;
import me.mayhem.game.entity.entities.drawable.healthbox.EntityHealthBox;
import me.mayhem.game.entity.event.EntityDamageByEntityEvent;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Entity Class
 * Used to store all the common information per entity in the game
 */
public abstract class Entity {

    private final EntityType type;
    private final Vector position;
    private final Vector motion;
    private final Pathing pathing;
    private final Hitbox hitbox;

    private final EntityState[] states = new EntityState[2];
    private final List<Attribute<?>> attributes =  new ArrayList<>();

    protected final EntityAnimation animate;

    private EntityState currentState;
    private Vector facing = new Vector(1, 0);
    private boolean entityFall = true;
    private boolean entityForward = false;
    private boolean entityBack = false;
    private boolean entityJump = false;
    private boolean entityStanding = false;
    private boolean entityGrounded = true;

    private double health;
    private boolean entityMelee = false;
    private boolean dead = false;

    protected boolean attacked = false;
    protected EntityPhysics entityPhysics;
    protected EntityHealthBox healthBox;

    protected final Clock attackedAnimateClock = new Clock();

    /**
     *
     * Entity Constructor
     *
     * @param type - Type of Entity - eg Player/Enemy
     * @param position - Current Position of entity relative to the game window
     * @param motion - Motion of the entity eg if entity is moving
     * @param pathing - Pathing for AI generated movement
     * @param attributes - Attributes an entity has - eg health
     */
    public Entity(EntityType type, Vector position, Vector motion, Hitbox hitbox, Pathing pathing, Attribute<?>... attributes) {
        this.type = type;
        this.position = position;
        this.motion = motion;
        this.pathing = pathing;
        this.hitbox = hitbox;
        this.health = type.getMaxHealth();
        this.attributes.addAll(Arrays.asList(attributes));
        this.entityPhysics = new EntityPhysics(this.type, motion);
        this.animate = new EntityAnimation(type);

        if (this.type.getHasHealthBar()) {
            this.healthBox = new EntityHealthBox(this, this.position);
        }

        this.animate.setSpritePosition(position.toVector());
        this.setState(EntityState.FALLING);
        this.setState(EntityState.STANDING);
    }

    public EntityAnimation getAnimation() {
        return this.animate;
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

    public double getHealth() {
        return this.health;
    }

    public void damage(Entity cause, double damage) {
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(this, cause, damage);

        EventManager.callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        this.health -= event.getDamage();
    }

    public EntityHealthBox getHealthBox() {
        return this.healthBox;
    }

    public List<Attribute<?>> getAttributes() {
        return this.attributes;
    }

    @SuppressWarnings("unchecked")
    public <T> Attribute<T> getAttribute(String identifier, Class<T> typeClass) {
        for (Attribute<?> attribute : this.attributes) {
            if (attribute == null || !Objects.equals(typeClass, attribute.getValue().getClass())) {
                continue;
            }

            if (attribute.getIdentifier().equals(identifier)) {
                return (Attribute<T>) attribute;
            }
        }

        return null;
    }

    public Attribute<?> getAttribute(String identifier) {
        for (Attribute<?> attribute : this.attributes) {
            if (attribute == null) {
                continue;
            }

            if (attribute.getIdentifier().equals(identifier)) {
                return attribute;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> void setAttribute(String identifier, T value) {
        Attribute<?> alreadyExists = this.getAttribute(identifier);

        if (alreadyExists != null) {
            if (!Objects.equals(alreadyExists.getValue().getClass(), value.getClass())) {
                return;
            }

            ((Attribute<T>) alreadyExists).setValue(value);
        } else {
            this.attributes.add(AttributeFactory.from(identifier, value));
        }
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

    public boolean isMelee() {
        return this.entityMelee;
    }

    public void setMelee(boolean entityMelee) {
        this.entityMelee = entityMelee;
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
        return this.hitbox.getHeight();
    }

    public float getWidth() {
        return this.hitbox.getWidth();
    }

    /**
     *
     * Updates position of the {@link me.mayhem.game.entity.player.Player} depending on user input
     * Outputs onto main window
     *
     * @param window The window being drawn on to
     */
    public void update(RenderWindow window) {
        animate.playAnimation(window);

        if (this.type.getHasHealthBar()) {
            healthBox.draw(window, this);
        }
    }

    public void tick() {
        if (this.isFalling()) {
            this.getEntityPhysics().fall();
        }

        if (this.isJumping()) {
            this.getEntityPhysics().jump();
        }

        if (this.attackedAnimateClock.getElapsedTime().asMilliseconds() >= 100 && this.attacked) {
            this.getAnimation().setColor(Color.WHITE);
        } else if (this.attacked){
            this.getAnimation().setColor(Color.RED);
        }

        if (this.isForward()) {
            this.getEntityPhysics().moveForward();
            this.animate.setRow(11);
            this.animate.setPause(false);
        } else if (this.isBack()) {
            this.getEntityPhysics().moveBack();
            this.animate.setRow(9);
            this.animate.setPause(false);
        }

        if(this.isMelee() && this.getFacing().getX() == 1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(15);
            this.animate.setPause(false);
        } else if (isMelee() && this.getFacing().getX() == -1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(13);
            this.animate.setPause(false);
        }

        this.animate.setSpritePosition(this.getPosition().toVector());
    }

    public void setTexture(Texture texture) {}

    /**
     *
     * Keyboard press listener sends a player state depending on which key has been pressed
     *
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
                animate.resetTimeOutClock();
                animate.setPause(true);
                this.setForward(false);
                this.setBack(false);
                this.setMelee(false);
            } else if (state == EntityState.BACK) {
                animate.setTimeOut(Integer.MAX_VALUE);
                animate.resetTimeOutClock();
                this.setForward(false);
                this.setBack(true);
                this.setMelee(false);
                animate.setAvailableFrames(9);
                this.facing = new Vector(-1, 0);
            } else if (state == EntityState.FORWARD) {
                animate.setTimeOut(Integer.MAX_VALUE);
                animate.resetTimeOutClock();
                this.setForward(true);
                this.setBack(false);
                this.setMelee(false);
                animate.setAvailableFrames(9);
                this.facing = new Vector(1, 0);
            } else if (state == EntityState.MELEE) {
                this.setMelee(true);
                animate.setTimeOut(340);
                animate.resetTimeOutClock();
            }
        }
    }

    public EntityState getState(int index) {
        return this.states[index];
    }

    public EntityState getPreviousState() {
        return this.currentState;
    }

    public boolean inBoundsY(Vector position) {
        if (position.getY() > (this.getPosition().getY() + this.getHeight())) {
            return false;
        }

        return position.getY() >= this.position.getY();
    }

    public boolean inBoundsX(Vector position) {
        if (position.getX() > (this.getPosition().getX() + this.getWidth())) {
            return false;
        }

        return position.getX() >= this.getPosition().getX();
    }

    public Vector getFacing() {
        return this.facing;
    }

    public Vector getCenter() {
        return this.getPosition().clone().add(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
    }

    public boolean isEntityGrounded() {
        return entityGrounded;
    }

    public void setEntityGrounded(boolean entityGrounded) {
        this.entityGrounded = entityGrounded;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
