package me.mayhem.game.entity.player.inventory;

import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Item {

    private final String identifier;
    private final Sprite sprite;

    private int amount;

    public Item(String identifier, Sprite sprite, int amount) {
        this.identifier = identifier;
        this.sprite = sprite;
        this.amount = amount;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void draw(RenderWindow window, Vector vector) {
        this.sprite.setPosition(vector.toVector());
        window.draw(this.sprite);
    }
}
