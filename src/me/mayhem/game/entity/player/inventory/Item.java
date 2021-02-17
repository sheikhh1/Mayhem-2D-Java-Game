package me.mayhem.game.entity.player.inventory;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Item {

    private final Sprite sprite;

    private int amount;

    public Item(Sprite sprite, int amount) {
        this.sprite = sprite;
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void draw(RenderWindow window) {
        window.draw(this.sprite);
    }
}
