package me.mayhem.game.entity.player.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return this.items;
    }

    public boolean contains(String id) {
        for (Item item : this.items) {
            if (item.getIdentifier().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
