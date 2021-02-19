package me.mayhem.game.entity.animation;

import me.mayhem.game.entity.EntityType;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 * Class created to output correct animations depending on user input for the player entity
 */
public class EntityAnimation {

    private final Sprite entitySprite;
    private final Clock animationUpdate = new Clock();
    private final Clock timeOutClock =  new Clock();

    private int row = 11; // Row of PlayerSheet.png
    private int column = 0;// Column of PlayerSheet.png
    private boolean pause = true; // Required to pause or resume the animations
    private int frameCount = 0;
    private int availableFrames = 1;
    private int timeOut = Integer.MAX_VALUE;
    public boolean entityDead = false;

    public EntityAnimation(EntityType entityType) {
        this.entitySprite = new Sprite(entityType.getEntityTexture());
        this.entitySprite.setScale(1.1f,1.1f);
        this.entitySprite.setTextureRect(new IntRect(this.getColumn() * 64,this.getRow() * 64,64,64));
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setColor(Color spriteColor) {
        this.entitySprite.setColor(spriteColor);
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setSpritePosition(Vector2f position){
        entitySprite.setPosition(position);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setEntityDead(boolean entityDead) {
        this.entityDead = entityDead;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void resetTimeOutClock() {
        this.timeOutClock.restart();
    }

    public void setAvailableFrames(int availableFrames) {
        this.availableFrames = availableFrames;
    }

    /**
     * Creates an animation effect and changes sprite texture accordingly every 50ms
     * @param window - Window passed to draw onto
     */
    public void playAnimation(RenderWindow window) {
        if(!pause && timeOutClock.getElapsedTime().asMilliseconds() <= timeOut){
            if (animationUpdate.getElapsedTime().asMilliseconds() >= 50){
                animationUpdate.restart();
                frameCount++;
                if (frameCount > this.availableFrames - 1) {
                    frameCount = 0;
                }
                this.setColumn(frameCount % this.availableFrames);
            }
        } else {
            if (this.entityDead) {
                this.setColumn(5);
            } else {
                this.setColumn(0);
            }
        }

        entitySprite.setTextureRect(new IntRect(this.getColumn() * 64 + 16,this.getRow() * 64 + 12,40,76 - 22));

        window.draw(entitySprite);
    }

    public float getHeight() {
        return this.entitySprite.getGlobalBounds().height;
    }

    public float getWidth() {
        return this.entitySprite.getGlobalBounds().width;
    }

    public Sprite getSprite() {
        return this.entitySprite;
    }
}
