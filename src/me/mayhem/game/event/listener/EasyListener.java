package me.mayhem.game.event.listener;

import me.mayhem.game.event.EventManager;

public class EasyListener {

    public EasyListener() {
        EventManager.registerListener(this);
    }
}
