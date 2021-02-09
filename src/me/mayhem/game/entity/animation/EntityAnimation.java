package me.mayhem.game.entity.animation;

import me.mayhem.game.entity.EntityType;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 * Class created to output correct animations depending on user input for the player entity
 */
public class EntityAnimation {

    private Sprite entitySprite;
    private int row = 11; // Row of PlayerSheet.png
    private int column = 0;// Column of PlayerSheet.png
    private boolean pause = true; // Required to pause or resume the animations
    private int frameCount = 0;
    private int availableFrames = 1;
    private Clock animationUpdate = new Clock();
    private Clock timeOutClock =  new Clock();
    private int timeOut = 0;

    public EntityAnimation(EntityType entityType) {

        this.entitySprite = new Sprite(entityType.getEntityTexture());
        // Increase the size of the sprite by 1.3x
        this.entitySprite.setScale(1.3f,1.3f);
        this.entitySprite.setTextureRect(new IntRect(this.getColumn() * 64,this.getRow() * 64,64,64));
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
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

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeOut() {
        return this.timeOut;
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
        //TODO: Fix Timeout (Buggy)
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
            this.setColumn(0);
        }

        entitySprite.setTextureRect(new IntRect(this.getColumn() * 64 + 18,this.getRow() * 64 + 12,30,76 - 22));

        window.draw(entitySprite);
    }

    public float getHeight() {
        return this.entitySprite.getGlobalBounds().height;
    }

    public float getWidth() {
        return this.entitySprite.getGlobalBounds().width;
    }

    public FloatRect getGlobalBounds() {
        return this.entitySprite.getGlobalBounds();
    }

    public FloatRect getLocalBounds() {
        return this.entitySprite.getLocalBounds();
    }

    public Sprite getSprite() {
        return this.entitySprite;
    }
}
