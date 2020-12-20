package me.mayhem.entity.sprites.player.animation;


import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

public class PlayerAnimation {

    private Texture playerTexture = new Texture();
    private Sprite playerSprite = new Sprite(playerTexture);
    private int row = 11;
    private int column = 0;

    public PlayerAnimation() {
        try {
            playerTexture.loadFromFile(Paths.get("src/me/mayhem/entity/sprites/player/images/PlayerSheet.png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        playerSprite.setScale(1.3f,1.3f);
        playerSprite.setTextureRect(new IntRect(column * 64,row * 64,64,64));

    }
}

