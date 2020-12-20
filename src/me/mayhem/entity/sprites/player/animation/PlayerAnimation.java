package me.mayhem.entity.sprites.player.animation;


import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Class created to output correct animations depending on user input for the player entity
 */
public class PlayerAnimation {

    private Texture playerTexture = new Texture();
    private Sprite playerSprite = new Sprite(playerTexture);
    private int row = 11; // Row of PlayerSheet.png
    private int column = 0;// Column of PlayerSheet.png
    private boolean pause = true; // Required to pause or resume the animations

    public PlayerAnimation() {
        try {
            playerTexture.loadFromFile(Paths.get("src/me/mayhem/entity/sprites/player/images/PlayerSheet.png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        // Increase the size of the sprite by 1.3x
        playerSprite.setScale(1.3f,1.3f);
        playerSprite.setTextureRect(new IntRect(column * 64,row * 64,64,64));
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
        playerSprite.setPosition(position);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}

