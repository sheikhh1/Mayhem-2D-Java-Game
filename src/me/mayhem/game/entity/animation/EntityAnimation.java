package me.mayhem.game.entity.animation;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 * Class created to output correct animations depending on user input for the player entity
 */
public class EntityAnimation {

    private Texture entityTexture;
    private Sprite entitySprite;
    private int row = 11; // Row of PlayerSheet.png
    private int column = 0;// Column of PlayerSheet.png
    private boolean pause = true; // Required to pause or resume the animations
    private int frameCount = 0;
    private Clock animationUpdate = new Clock();

    public EntityAnimation() {
        entityTexture = UtilImageLoader.loadTextureFromStream(getClass().getClassLoader().getResourceAsStream("players/PlayerSheet.png"));
        entitySprite = new Sprite(entityTexture);
        // Increase the size of the sprite by 1.3x
        entitySprite.setScale(1.3f,1.3f);
        entitySprite.setTextureRect(new IntRect(this.getColumn() * 64,this.getRow() * 64,64,64));
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

    /**
     * Creates an animation effect and changes sprite texture accordingly every 50ms
     * @param window - Window passed to draw onto
     */
    public void playAnimation(RenderWindow window) {
        if(!pause){
            if (animationUpdate.getElapsedTime().asMilliseconds() >= 50){
                animationUpdate.restart();
                frameCount++;
                if (frameCount > 8) frameCount = 0;
                this.setColumn(frameCount % 9);
            }
        }
        entitySprite.setTextureRect(new IntRect(this.getColumn() * 64 + 16,this.getRow() * 64,30,76));
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

